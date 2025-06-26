# Etapa 1: Build da aplicação com Node
FROM node:22-alpine AS builder

WORKDIR /app

COPY package*.json ./

# Instala as dependências (sem cache desnecessário)
RUN npm install

# Copia o restante dos arquivos do projeto
COPY . .

# Passa a variável ARG (vinda do docker-compose) para o Vite
ARG VITE_API_BACKEND_URL
ENV VITE_API_BACKEND_URL=${VITE_API_BACKEND_URL}


# Faz o build do Vite (gera a pasta dist/)
RUN npm run build

# Etapa 2: Servindo arquivos com Nginx
FROM nginx:alpine

# Remove a configuração padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos estáticos do build para a pasta do Nginx
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
