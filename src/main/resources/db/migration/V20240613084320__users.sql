create table if not exists public.users
(
    user_id         bigint    primary key,
    first_name text not null,
    last_name  text not null,
    created_at timestamp default now(),
    updated_at timestamp default now()
);
