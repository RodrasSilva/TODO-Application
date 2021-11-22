## Prepare build files
`npm install`

`npm run build`

## Create image and run a container with image
`docker build -t agi-frontend:latest .`

`docker run -it --name agi-frontend -p 3000:3000 agi-frontend:latest`

## Cleanup

`rm -rf node_modules`

`rm -rf build`

`docker container rm agi-frontend`

`docker image rm agi-frontend`





