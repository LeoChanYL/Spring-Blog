CREATE TABLE IF NOT EXISTS NAME1 (
                                    ID INT AUTO_INCREMENT PRIMARY KEY,
                                    NAME VARCHAR(255) NOT NULL,
                                    Image blob not null ,
                                    created_at TIMESTAMP not null ,
                                    user_id INT NOT NULL
);
CREATE TABLE IF NOT EXISTS Users1 (

                                     NAME VARCHAR(50) NOT NULL PRIMARY KEY,
                                    email VARCHAR(250) NOT NULL,
                                phone VARCHAR(50) NOT NULL,
                                 description VARCHAR(255)
);
Create Table IF NOT EXISTS Users (

                                username VARCHAR(50) NOT NULL PRIMARY KEY,
                                password VARCHAR(50) NOT NULL,
                                enabled bool NOT NULL
);

create table if not exists authorities (
                                        username varchar(50) not null primary key ,
                                        authority varchar(50) not null,

                                        constraint fk_authorities_users foreign key (username) references users (username)
);
create table if not exists comments (
                                    id int auto_increment primary key,
                                    comment varchar(255) not null,
                                    created_at timestamp not null default current_timestamp(),
                                    uid varchar(50) not null,
                                    pid int not null,
                                    constraint fk_comments_users foreign key (uid) references users (username),
                                    constraint fk_comments_posts foreign key (pid) references NAME1 (id)
);