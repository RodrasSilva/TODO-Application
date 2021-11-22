# Install istio
DIR="./istio-1.9.2"
if [ -d "$DIR" ]; then
    # Take action if $DIR exists. #
    echo "Istio already downloaded"
else
    curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.9.2 sh -
fi

# Install/Update the necessary plugins
terraform init
# Plan the defined infrastructure
terraform plan
# Create the defined infrastructure
terraform apply -auto-approve 
