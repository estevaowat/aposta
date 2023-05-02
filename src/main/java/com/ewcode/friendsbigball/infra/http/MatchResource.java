package com.ewcode.friendsbigball.infra.http;

import com.ewcode.friendsbigball.domain.common.entity.Match;
import com.ewcode.friendsbigball.domain.match.dto.MatchRequestDTO;
import com.ewcode.friendsbigball.domain.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/match")
@RestController
public class MatchResource {
    private final MatchService matchService;
    @Autowired
    public MatchResource(MatchService gameService) {
        this.matchService = gameService;
    }


    public ResponseEntity<Match> saveGame(MatchRequestDTO matchRequestDTO) {
        Match match = matchService.save(matchRequestDTO);
        return ResponseEntity.ok(match);
    }
}

