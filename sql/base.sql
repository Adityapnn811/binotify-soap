CREATE TABLE IF NOT EXISTS Logging (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description varchar(256) NOT NULL,
    IP varchar(16) NOT NULL,
    endpoint varchar(256) NOT NULL,
    requested_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Subscription (
    creator_id INT,
    subscriber_id INT,
    status ENUM('PENDING', 'ACCEPTED', 'REJECTED') NOT NULL DEFAULT 'PENDING',
    PRIMARY KEY(creator_id, subscriber_id)
);

CREATE TABLE IF NOT EXISTS ApiKeys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_name varchar(256) NOT NULL,
    api_key varchar(256) NOT NULL UNIQUE
);

INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (2, 3, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (3, 3, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (4, 3, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (2, 4, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (3, 4, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (4, 4, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (3, 5, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (4, 5, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (2, 2, 'PENDING');
INSERT INTO Subscription (creator_id, subscriber_id, status) VALUES (4, 2, 'PENDING');