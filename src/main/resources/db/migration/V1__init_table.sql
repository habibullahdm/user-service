CREATE TABLE tb_user (
    id VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    roles VARCHAR2(10) NOT NULL,
    updated_by VARCHAR2(36) NOT NULL,
    updated_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR2(36) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT tb_user_id PRIMARY KEY (id)
);
