package com.example.hnefatafl;

import com.example.hnefatafl.model.Game;
import com.example.hnefatafl.repository.GameRepository;
import com.example.hnefatafl.service.GameService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Test
    void testStartNewGame() {
        GameRepository repo = mock(GameRepository.class);
        GameService service = new GameService(repo);

        Game input = new Game("STANDARD");
        Game saved = new Game("STANDARD");
        saved.setId("1");

        when(repo.save(any())).thenReturn(saved);

        Game result = service.startNewGame("STANDARD");

        assertEquals("STANDARD", result.getType());
        assertNotNull(result.getId());
    }
}
