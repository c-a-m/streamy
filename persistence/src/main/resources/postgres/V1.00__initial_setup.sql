CREATE TABLE users
(
    user_id bigint NOT NULL,
    contact_name character varying(245) COLLATE pg_catalog."default" NOT NULL,
    create_date timestamp without time zone DEFAULT now(),
    update_date timestamp without time zone DEFAULT now(),
    login_name character varying(65) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(120) COLLATE pg_catalog."default",
    last_name character varying(120) COLLATE pg_catalog."default",
    display_name character varying(245) COLLATE pg_catalog."default",
    email character varying(320) COLLATE pg_catalog."default",
    salary bigint,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT uk_login_name UNIQUE (login_name),
    CONSTRAINT uk_contact_name UNIQUE (contact_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE SEQUENCE SEQ_USERS START WITH 1000;