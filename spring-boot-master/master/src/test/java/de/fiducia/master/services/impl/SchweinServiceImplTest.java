package de.fiducia.master.services.impl;

import de.fiducia.master.repositories.entities.SchweinEntity;
import de.fiducia.master.repositories.impl.SchweineRepo;
import de.fiducia.master.services.SchweineServiceException;
import de.fiducia.master.services.mapper.SchweinMapper;
import de.fiducia.master.services.mapper.SchweinMapperImpl;
import de.fiducia.master.services.models.Schwein;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchweinServiceImplTest {

    @InjectMocks
    private SchweinServiceImpl objectUnderTest;


    @Spy
    private SchweinMapper schweinMapper = new SchweinMapperImpl();

  //  @Mock
  //  private SchweinMapper schweinMapperMock;


    @Mock
    private SchweineRepo repoMock;

    @Test
    void fuettereSchweinMitId_PigNotFound_returnsFalse () throws  Exception {
        when(repoMock.findById(anyString())).thenReturn(Optional.empty());

        var result = objectUnderTest.fuettereSchweinMitId("123");

        assertFalse(result);
    }
    @Test
    void fuettereSchweinMitId_ExceptionInUnderliyingService_throwsSchweineServiceExceptionWithMessageUpps () throws  Exception {
        when(repoMock.findById(anyString())).thenThrow(new ArrayIndexOutOfBoundsException(1000));

        SchweineServiceException ex = assertThrows(SchweineServiceException.class, ()-> objectUnderTest.fuettereSchweinMitId("123"));

        assertEquals("Upps", ex.getMessage());
    }

    @Test
    void fuettereSchweinMitId_PigExists_fatPigPassedToRepo () throws  Exception {

        SchweinEntity fritz = SchweinEntity.builder().id("123").name("Fritz").gewicht(10).build();
        Optional<SchweinEntity> optinalFritz = Optional.of(fritz);

        when(repoMock.findById(anyString())).thenReturn(optinalFritz);

        var result = objectUnderTest.fuettereSchweinMitId("123");

        assertTrue(result);

        SchweinEntity expectedPig = SchweinEntity.builder().id("123").name("Fritz").gewicht(11).build();
        verify(repoMock).save(expectedPig);
    }

}