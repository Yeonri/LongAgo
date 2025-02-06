package com.example.b101.repository;

import com.example.b101.cache.Game;
import com.example.b101.domain.PlayerStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepoImpl implements GameRepository {

    private static final String KEY = "game";

    private final RedisTemplate<String, Game> redisTemplate;

    public GameRepoImpl(RedisTemplate<String, Game> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Game game) {
        redisTemplate.opsForHash().put(KEY, game.getGameId(), game);
    }

    @Override
    public void delete(Game game) {
        redisTemplate.opsForHash().delete(KEY, game.getGameId());
    }

    @Override
    public void update(Game game) {
        redisTemplate.opsForHash().put(KEY, game.getGameId(), game);
    }

    @Override
    public Game findById(String id) {
        return (Game) redisTemplate.opsForHash().get(KEY,id);
    }

    public PlayerStatus getPlayerStatus(String gameId, String playerId) {
        Game game = findById(gameId);

        List<PlayerStatus> playerStatuses = game.getPlayerStatuses();

        for (PlayerStatus playerStatus : playerStatuses) {
            if(playerStatus.getUserId().equals(playerId)) {
                return playerStatus;
            }
        }

        return null;
    }
}
