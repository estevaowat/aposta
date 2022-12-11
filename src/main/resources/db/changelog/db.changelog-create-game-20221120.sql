create table if not exists game
(
    start          datetime not null primary key,
    status         enum ('NOT_STARTED', 'IN_PROGRESS', 'FINISHED'),
    winner_team_id int      null,
    game_score     varchar(10),
    best_player_id int      null,
    foreign key (winner_team_id) references team (id),
    foreign key (best_player_id) references player (id)
);