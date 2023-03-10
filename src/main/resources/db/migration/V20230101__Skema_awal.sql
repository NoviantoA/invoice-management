CREATE TABLE running_number(
   id character varying(36) NOT NULL,
   prefix character varying(100) NOT NULL ,
   last_number bigint NOT NULL
);

CREATE TABLE customer (
  id character varying(36) NOT NULL,
  create_by character varying(255),
  created timestamp(6) without time zone,
  status_record character varying(255) NOT NULL,
  update timestamp(6) without time zone,
  updated_by character varying(255),
  code character varying(100) NOT NULL,
  name character varying(255) NOT NULL,
  email character varying(100) NOT NULL,
  mobile_phone character varying(30) NOT NULL
);

CREATE TABLE invoice (
    id character varying(36) NOT NULL,
    create_by character varying(255),
    created timestamp(6) without time zone,
    status_record character varying(255) NOT NULL,
    update timestamp(6) without time zone,
    updated_by character varying(255),
    amount numeric(38,2) NOT NULL,
    total_payment numeric(38,2) NOT NULL ,
    description character varying(255) NOT NULL,
    due_date date NOT NULL,
    invoice_number character varying(100) NOT NULL,
    paid boolean NOT NULL,
    id_invoice_type character varying(36) NOT NULL,
    id_customer character varying(36) NOT NULL,
    CONSTRAINT invoice_amount_check CHECK ((amount >= (0)::numeric))
);

CREATE TABLE invoice_type (
     id character varying(36) NOT NULL,
     create_by character varying(255),
     created timestamp(6) without time zone,
     status_record character varying(255) NOT NULL,
     update timestamp(6) without time zone,
     updated_by character varying(255),
     code character varying(100) NOT NULL,
     name character varying(100) NOT NULL,
     payment_type character varying(50) NOT NULL
);

CREATE TABLE payment (
    id character varying(36) NOT NULL,
    create_by character varying(255),
    created timestamp(6) without time zone,
    status_record character varying(255) NOT NULL,
    update timestamp(6) without time zone,
    updated_by character varying(255),
    amount numeric(38,2) NOT NULL,
    provider_reference character varying(255) NOT NULL,
    transaction_time timestamp(6) without time zone NOT NULL,
    id_virtual_account character varying(255) NOT NULL,
    CONSTRAINT payment_amount_check CHECK ((amount >= (1)::numeric))
);

CREATE TABLE payment_provider (
     id character varying(36) NOT NULL,
     code character varying(100) NOT NULL,
     name character varying(100) NOT NULL,
     create_by character varying(255),
     created timestamp(6) without time zone,
     status_record character varying(255) NOT NULL,
     update timestamp(6) without time zone,
     updated_by character varying(255),
     logo character varying(255)
);

CREATE TABLE virtual_account (
    id character varying(36) NOT NULL,
    create_by character varying(255),
    created timestamp(6) without time zone,
    status_record character varying(255) NOT NULL,
    update timestamp(6) without time zone,
    updated_by character varying(255),
    account_number character varying(255) NOT NULL,
    company_id character varying(255) NOT NULL,
    virtual_account_type character varying(255) NOT NULL,
    id_invoice character varying(255) NOT NULL,
    id_payment_provider character varying(255) NOT NULL
);

CREATE TABLE invoice_type_provider (
   id_invoice_type character varying(36) NOT NULL ,
   id_payment_provider character varying(36) NOT NULL
);


-- set primary key
ALTER TABLE running_number
    ADD CONSTRAINT running_number_pkey PRIMARY KEY (id);

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_unique_code UNIQUE (code);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT virtual_account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT invoice_type_provider_pkey PRIMARY KEY (id_invoice_type, id_payment_provider);

-- set foreign key

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkbbdwdxpgdisiikyyhf2xteblc FOREIGN KEY (id_invoice) REFERENCES invoice(id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fk_invoice_customer FOREIGN KEY (id_customer) REFERENCES customer(id);

ALTER TABLE ONLY payment
    ADD CONSTRAINT fkptriq88d7e8io9mhri8p10cq0 FOREIGN KEY (id_virtual_account) REFERENCES virtual_account(id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkt3t7f64hvgk4xjblsovqqkpll FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_type FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);
ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_provider FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);
