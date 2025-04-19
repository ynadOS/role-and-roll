# Étape 1 : Build Angular
FROM node:20 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Étape 2 : Serveur NGINX pour hoster l'app
FROM nginx:alpine

# On copie la build Angular vers le dossier public de NGINX
COPY --from=build /app/dist/front/browser /usr/share/nginx/html

# On remplace la config NGINX par une version custom adaptée à Angular SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
