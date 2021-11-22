# Terraform google cloud multi tier Kubernetes deployment

#################################################################
# Definition of the Services
#################################################################


# MongoDB Service
resource "kubernetes_service" "mongodb" {
  metadata {
    name = "mongodb-service"

    labels = {
      app  = "mongodb"
      tier = "database"
    }
    
    namespace = kubernetes_namespace.application.id 
  }

  spec {
    selector = {
      app  = "mongodb"
      tier = "database"
    }

    port {
      port        = 27017
      target_port = 27017
    }
  }
}

# Backend Service
resource "kubernetes_service" "backend" {
  metadata {
    name = "backend-service"

    labels = {
      app  = "backend"
      tier = "backend"
    }

    namespace = kubernetes_namespace.application.id 
  }

  spec {
    selector = {
      app  = "backend"
      tier = "backend"
    }

    type = "LoadBalancer"

    port {
      port = 8080
      target_port = 8080
    }
  }
}

#################################################################
# The Service for the Frontend Load Balancer Ingress
resource "kubernetes_service" "frontend" {
  metadata {
    name = "frontend"

    labels = {
      app  = "todo"
      tier = "frontend"
    }

    namespace = kubernetes_namespace.application.id 
  }

  spec {
    selector = {
      app  = "todo"
      tier = "frontend"
    }

    type = "LoadBalancer"

    port {
      port = 80
      target_port = 3000
    }
  }
}

