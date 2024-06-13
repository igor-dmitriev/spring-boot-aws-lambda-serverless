create sequence if not exists public.users_id_seq;

create table if not exists public.users
(
    id         bigint    default nextval('users_id_seq') primary key,
    first_name text not null,
    last_name  text not null,
    created_at timestamp default now(),
    updated_at timestamp default now()
);
