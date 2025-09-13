INSERT INTO users (
    cin, first_name, last_name, email, telephone, birth_date, gender, address, keycloak_id, creation_timestamp, last_modification_timestamp
) VALUES
      (1, 'Alice', 'Dupont', 'alice.dupont@example.com', '0612345678', '1990-05-15', 'FEMALE', '123 Rue de Paris', 'keycloak-uuid-1', NOW(), NOW()),
      (2, 'Bob', 'Martin', 'bob.martin@example.com', '0698765432', '1985-09-30', 'MALE', '45 Avenue Victor Hugo', 'keycloak-uuid-2', NOW(), NOW()),
      (3, 'Charlie', 'Durand', 'charlie.durand@example.com', '0755123456', '2000-01-20', 'MALE', '8 Boulevard Haussmann', 'keycloak-uuid-3', NOW(), NOW()),
      (4, 'Diane', 'Petit', 'diane.petit@example.com', '0601020304', '1995-12-01', 'FEMALE', '11 Rue Lafayette', 'keycloak-uuid-4', NOW(), NOW()),
      (5, 'Eric', 'Blanc', 'eric.blanc@example.com', '0677889900', '1988-07-18', 'MALE', '22 Rue Mouffetard', 'keycloak-uuid-5', NOW(), NOW()),
      (6, 'Fanny', 'Lopez', 'fanny.lopez@example.com', '0666777888', '1992-03-25', 'FEMALE', '33 Avenue des Champs', 'keycloak-uuid-6', NOW(), NOW()),
      (7, 'Gilles', 'Moreau', 'gilles.moreau@example.com', '0655443322', '1983-11-05', 'MALE', '44 Rue Oberkampf', 'keycloak-uuid-7', NOW(), NOW()),
      (8, 'Helene', 'Faure', 'helene.faure@example.com', '0622334455', '1997-06-09', 'FEMALE', '55 Rue de Rennes', 'keycloak-uuid-8', NOW(), NOW()),
      (9, 'Ivan', 'Marchand', 'ivan.marchand@example.com', '0644556677', '1990-02-14', 'MALE', '66 Rue de Rivoli', 'keycloak-uuid-9', NOW(), NOW()),
      (10, 'Julie', 'Bernard', 'julie.bernard@example.com', '0633445566', '1989-10-22', 'FEMALE', '77 Boulevard Voltaire', 'keycloak-uuid-10', NOW(), NOW());
