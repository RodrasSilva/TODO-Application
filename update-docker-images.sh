# Requires login in rodrasit account, login with the command -> docker login

# Build frontend image
echo "----------------Building todo-frontend image----------------"
docker build -t rodrasit/todo-frontend:latest ./frontend
echo "----------------Done----------------"

# Push frontend image to docker hub
echo "----------------Pushing todo-frontend image to Docker Hub----------------"
docker push rodrasit/todo-frontend
echo "----------------Done----------------"

# Build backend image
echo "----------------Building todo-service image----------------"
docker build -t rodrasit/todo-service:latest ./backend
echo "----------------Done----------------"

# Push backend image to docker hub
echo "----------------Pushing todo-service image to Docker Hub----------------"
docker push rodrasit/todo-service
echo "----------------Done----------------"
