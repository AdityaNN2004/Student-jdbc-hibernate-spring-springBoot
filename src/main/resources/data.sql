USE studentdb;

INSERT INTO users (email, password, user_role, created_on, updated_on, is_deleted) 
VALUES (
    'system.admin@school.com', 
    '$2a$10$7vP51m69iU5E0Yp.WjS6v.pQvL49bL9F26L.gq87E6D7g3D9g2F92', -- Secure BCrypt Hash for 'AdminSecure123'
    'ADMIN', 
    NOW(), -- Satisfies your metadata auditing column requirements
    NOW(), 
    FALSE  -- Soft-delete flag active fallback baseline
);
