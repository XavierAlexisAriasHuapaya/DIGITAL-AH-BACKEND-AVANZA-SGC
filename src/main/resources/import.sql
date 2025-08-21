INSERT INTO master_catalog (name, status) VALUES ('Countries', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Peru', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Chile', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Colombia', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Argentina', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Ecuador', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (1, 'Mexico', true);

INSERT INTO master_catalog (name, status) VALUES ('Document Type', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'DNI', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'RUC', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'CE', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'RUT', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'CUIT', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'NIF', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'CC', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (2, 'CI', true);

INSERT INTO master_catalog (name, status) VALUES ('Currency', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (3, 'PEN', true);
INSERT into catalog_items (master_catalog_id, name, status) VALUES (3, 'USD', true);

INSERT INTO companies (country_id, business_name, trade_name, tax_id, address, city, phone, email, created_at, updated_at, status) VALUES (1, 'SGC', 'AH', '20191095025', 'Av. Siempre Viva 123', 'Lima', '900192119', 'sgc@ah.org', NOW(), NOW(), true);

INSERT INTO roles (company_id, name, description, created_at, updated_at, status) VALUES (1, 'ROLE_ADMIN', 'Administrator role', NOW(), NOW(), true);
INSERT INTO roles (company_id, name, description, created_at, updated_at, status) VALUES (1, 'ROLE_USER', 'User role', NOW(), NOW(), true);

INSERT INTO users (rol_id, username, password) VALUES (1, 'admin', 'clave123');

INSERT INTO employees (company_id, document_type_id, user_id, first_name, last_name, address, city, phone, email, created_at, updated_at, status) VALUES (1, 1, 1, 'John', 'Doe', '123 Main St', 'Anytown', '555-1234', 'john.doe@example.com', NOW(), NOW(), true);