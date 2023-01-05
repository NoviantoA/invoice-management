CREATE TABLE public.invoice (
    id character varying(36) NOT NULL,
    create_by character varying(255),
    created timestamp(6) without time zone,
    status_record character varying(255) NOT NULL,
    update timestamp(6) without time zone,
    updated_by character varying(255),
    amount numeric(38,2) NOT NULL,
    description character varying(255) NOT NULL,
    due_date date NOT NULL,
    invoice_number character varying(100) NOT NULL,
    paid boolean NOT NULL,
    id_invoice_type character varying(255) NOT NULL,
    CONSTRAINT invoice_amount_check CHECK ((amount >= (0)::numeric))
);

CREATE TABLE public.invoice_type (
     id character varying(36) NOT NULL,
     create_by character varying(255),
     created timestamp(6) without time zone,
     status_record character varying(255) NOT NULL,
     update timestamp(6) without time zone,
     updated_by character varying(255),
     code character varying(100) NOT NULL,
     name character varying(100) NOT NULL
);

CREATE TABLE public.payment (
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

CREATE TABLE public.payment_provider (
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

CREATE TABLE public.virtual_account (
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

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.payment_provider
    ADD CONSTRAINT payment_provider_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.payment_provider
    ADD CONSTRAINT payment_provider_unique_code UNIQUE (code);

ALTER TABLE ONLY public.virtual_account
    ADD CONSTRAINT virtual_account_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT invoice_type_provider_pkey PRIMARY KEY (id_invoice_type, id_payment_provider);

-- set foreign key

ALTER TABLE ONLY public.virtual_account
    ADD CONSTRAINT fkbbdwdxpgdisiikyyhf2xteblc FOREIGN KEY (id_invoice) REFERENCES public.invoice(id);

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_invoice_type) REFERENCES public.invoice_type(id);

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT fkptriq88d7e8io9mhri8p10cq0 FOREIGN KEY (id_virtual_account) REFERENCES public.virtual_account(id);

ALTER TABLE ONLY public.virtual_account
    ADD CONSTRAINT fkt3t7f64hvgk4xjblsovqqkpll FOREIGN KEY (id_payment_provider) REFERENCES public.payment_provider(id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_type FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);
ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_provider FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);
