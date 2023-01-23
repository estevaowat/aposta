create table bet
(
    costumer_id    int         not null,
    game_id        datetime    not null,
    game_score     varchar(10) not null,
    winner_team_id int         null,
    best_player_id int         not null,
    points         int         null,
    status         enum ('WAITING', 'FINISHED'),
    PRIMARY KEY (costumer_id, game_id),
    foreign key (costumer_id) references costumer (id),
    foreign key (game_id) references game (start),
    foreign key (winner_team_id) references team (id),
    foreign key (best_player_id) references player (id)

);