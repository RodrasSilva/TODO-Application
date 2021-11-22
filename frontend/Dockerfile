#extend the original node image
FROM node:16.10.0
#expose frontend port to host
EXPOSE 3000
#copy project to container
COPY . .
#install dependencies
RUN npm install 
#run server
CMD ["npm", "start"]