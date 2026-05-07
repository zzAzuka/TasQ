-- =========================================
-- USERS
-- =========================================

CREATE TABLE users (
                       userid SERIAL PRIMARY KEY,
                       username VARCHAR(20) NOT NULL,
                       email VARCHAR(50) NOT NULL,
                       password VARCHAR(120) NOT NULL,

                       CONSTRAINT uq_users_username UNIQUE (username),
                       CONSTRAINT uq_users_email UNIQUE (email)
);

-- =========================================
-- ROLES
-- =========================================

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(20) NOT NULL,

                       CONSTRAINT uq_roles_name UNIQUE (name)
);

-- =========================================
-- PROJECTS
-- =========================================

CREATE TABLE projects (
                          projectid SERIAL PRIMARY KEY,
                          projectname VARCHAR(255),
                          projectdescription TEXT
);

-- =========================================
-- TASKS
-- =========================================

CREATE TABLE tasks (
                       taskid SERIAL PRIMARY KEY,

                       name VARCHAR(255),
                       description TEXT,
                       status VARCHAR(50),

                       deadline TIMESTAMP,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP,

                       userid INT,
                       projectid INT,

                       CONSTRAINT fk_tasks_user
                           FOREIGN KEY (userid)
                               REFERENCES users(userid),

                       CONSTRAINT fk_tasks_project
                           FOREIGN KEY (projectid)
                               REFERENCES projects(projectid)
);

-- =========================================
-- USER ROLE MEMBERSHIP
-- =========================================

CREATE TABLE userrolemembership (
                                    userid INT NOT NULL,
                                    roleid INT NOT NULL,

                                    PRIMARY KEY (userid, roleid),

                                    CONSTRAINT fk_userrole_user
                                        FOREIGN KEY (userid)
                                            REFERENCES users(userid),

                                    CONSTRAINT fk_userrole_role
                                        FOREIGN KEY (roleid)
                                            REFERENCES roles(id)
);

-- =========================================
-- USER PROJECT MEMBERSHIP
-- =========================================

CREATE TABLE userprojectmembership (
                                       userprojectid SERIAL PRIMARY KEY,

                                       userid INT NOT NULL,
                                       projectid INT NOT NULL,

                                       CONSTRAINT fk_userproject_user
                                           FOREIGN KEY (userid)
                                               REFERENCES users(userid),

                                       CONSTRAINT fk_userproject_project
                                           FOREIGN KEY (projectid)
                                               REFERENCES projects(projectid)
);

-- =========================================
-- INDEXES
-- =========================================

CREATE INDEX idx_tasks_userid
    ON tasks(userid);

CREATE INDEX idx_tasks_projectid
    ON tasks(projectid);

CREATE INDEX idx_userproject_userid
    ON userprojectmembership(userid);

CREATE INDEX idx_userproject_projectid
    ON userprojectmembership(projectid);