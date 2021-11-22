# Terraform google cloud multi tier Kubernetes deployment

resource "kubernetes_namespace" "istio_system" {
  metadata {
    name = "istio-system"
  }
}

resource "kubernetes_namespace" "application" {
  metadata {
    name = "application"
   
    labels = {
      istio-injection = "enabled"
    }
  }
}