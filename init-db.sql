-- Create Keycloak database and user
CREATE USER keycloak_user WITH PASSWORD 'keycloak_pass';
CREATE DATABASE keycloak_db OWNER keycloak_user;

-- Create app database and user
CREATE USER spring_user WITH PASSWORD 'password';
CREATE DATABASE "ePrescriptionServer_db" OWNER spring_user;