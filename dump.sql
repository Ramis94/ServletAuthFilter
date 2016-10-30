-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  login character varying(255) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;

-- Index: public.user_email_uindex

-- DROP INDEX public.user_email_uindex;

CREATE UNIQUE INDEX user_email_uindex
  ON public."user"
  USING btree
  (email COLLATE pg_catalog."default");

-- Index: public.user_login_uindex

-- DROP INDEX public.user_login_uindex;

CREATE UNIQUE INDEX user_login_uindex
  ON public."user"
  USING btree
  (login COLLATE pg_catalog."default");

