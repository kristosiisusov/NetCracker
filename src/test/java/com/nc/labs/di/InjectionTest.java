package com.nc.labs.di;

import com.nc.labs.agreements.Agreement;
import com.nc.labs.repositories.IRepository;
import com.nc.labs.sorts.BaseSort.BubbleSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class InjectionTest {
    @Test
    public void testInjViaField() throws Exception {
        IRepository<Agreement> repository = (IRepository<Agreement>) InjectionAnalyzer.injViaField("com.nc.labs.repositories", "com.nc.labs.sorts.BaseSort");
        assertEquals(repository.getTypeOfSort().getClass(), BubbleSort.class);
    }

    @Test
    void testBindingExceptionMultipleCandidates() {
        String errorMessage = "Choose needing class to inject:";
        Throwable exception = assertThrows(BindingException.class, () -> {
            InjectionAnalyzer.injViaField("com.nc.labs.repositories", "");
        });
        assertEquals("Choose needing class to inject:", exception.getMessage().substring(0, errorMessage.length()));
    }
    @Test
    void testBindingExceptionNoCandidates() {
        Throwable exception = assertThrows(BindingException.class, () -> {
            InjectionAnalyzer.injViaField("com.nc.labs.repositories", "com.nc.labs.people");
        });
        assertEquals("No matching elements were found", exception.getMessage());
    }
}
