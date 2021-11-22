# Terraform google cloud multi tier Kubernetes deployment

#################################################################
# Definition of the Pods
#################################################################

# see: https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs/resources/replication_controller

# MongoDB Kubernetes Deployment
resource "kubernetes_deployment" "mongodb" {
  metadata {
    name = "mongodb-deployment"
    labels = {
      app  = "mongodb"
      tier = "database"
    }

    namespace = kubernetes_namespace.application.id
  }

  depends_on = [
    helm_release.istiod,
    kubernetes_namespace.application
  ]

  spec {
    progress_deadline_seconds = 1200 # In case of taking longer than 9 minutes
    replicas = 1
    selector {
      match_labels = {
        app  = "mongodb"
      }
    }

    template {
      metadata {
        labels = {
          app  = "mongodb"
          tier = "database"
        }
      }

      spec {
        container {
          image = "mongo"
          name  = "mongodb"
          
          port {
            #MongoDB Port
            container_port = 27017
          }

          env {
            name  = "MONGO_INITDB_ROOT_USERNAME"
            value = "username"
          }

          env {
            name  = "MONGO_INITDB_ROOT_PASSWORD"
            value = "password"
          }
        }
      }
    }
  }
}


# Backend Kubernetes Deployment
resource "kubernetes_deployment" "backend" {
  metadata {
    name = "backend-deployment"

    labels = {
      app  = "backend"
      tier = "backend"
    }
    
    namespace = kubernetes_namespace.application.id
  }

  depends_on = [
    helm_release.istiod,
    kubernetes_namespace.application,
    kubernetes_deployment.mongodb,
  ]

  spec {
    progress_deadline_seconds = 1200 # In case of taking longer than 9 minutes
    replicas = 1
    selector {
      match_labels = {
        app  = "backend"
      }
    }
    template {
      metadata {
        labels = {
          app  = "backend"
          tier = "backend"
        }
      }

      spec {
        container {
          image = "rodrasit/todo-service"
          name  = "backend"

          port {
            container_port = 8080 # Backend port
          }

          env {
            name  = "spring.data.mongodb.host"
            value = "mongodb-service"
          }
          
          env {
            name  = "spring.data.mongodb.username"
            value = "username"
          }
          
          env {
            name  = "spring.data.mongodb.password"
            value = "password"
          }

          env {
            name  = "GET_HOSTS_FROM"
            value = "dns"
          }
        }
      }
    }
  }
}

# Frontend Kubernetes Deployment
resource "kubernetes_deployment" "frontend" {
  metadata {
    name = "frontend-deployment"

    labels = {
      app  = "todo"
      tier = "frontend"
    }
    
    namespace = kubernetes_namespace.application.id
  }

  depends_on = [
    helm_release.istiod,
    kubernetes_namespace.application,
    kubernetes_deployment.backend,
  ]

  spec {
    replicas = 1
    selector {
      match_labels = {
        app  = "todo"
        tier = "frontend"
      }
    }

    template {
      metadata {
        labels = {
          app  = "todo"
          tier = "frontend"
        }
      }
      spec {
        container {
          image = "rodrasit/todo-frontend"
          name  = "frontend"

          port {
            container_port = 3000 #Frontend port
          }

          env {
            name  = "REACT_APP_HOST_IP_ADDRESS"
            value = kubernetes_service.backend.status.0.load_balancer.0.ingress.0.ip
          }

          env {
            name  = "REACT_APP_HOST_PORT" 
            value = "8080"
          }

          env {
            name  = "GET_HOSTS_FROM"
            value = "dns"
          }
        }
      }
    }
  }
}
