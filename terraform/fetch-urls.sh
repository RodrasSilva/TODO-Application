gcloud container clusters get-credentials todo --region europe-west1 --project agisit-2021-website-07a
echo "-----------Fetching URL's------------"
echo "The frontend is hosted at:"
echo "http://"$(kubectl get services -n application | grep "frontend" | awk '{ print $4 }' )
echo ""
echo "Grafana is hosted at:"
echo "http://"$(kubectl get services -n istio-system | grep "grafana" | awk '{ print $4 }' )":3000"
echo ""
echo "Prometheus is hosted at:"
echo "http://"$(kubectl get services -n istio-system | grep "prometheus" | awk '{ print $4 }' )":9090"
echo ""