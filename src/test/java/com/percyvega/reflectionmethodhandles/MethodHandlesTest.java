package com.percyvega.reflectionmethodhandles;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Log4j2
public class MethodHandlesTest {

    public static final String PERCY = "Percy";
    public static final String FRANCESCA = "Francesca";

    Registrant registrant1;
    Registrant registrant2;

    MethodHandles.Lookup lookup;

    @BeforeEach
    void setUp() {
        registrant1 = new Registrant(PERCY);
        registrant2 = new Registrant(FRANCESCA);

        lookup = MethodHandles.lookup();
    }

    @Test
    void call_constructor_by_using_methodHandles() throws Throwable {
        MethodHandle constructor = lookup.findConstructor(Registrant.class, MethodType.methodType(void.class, String.class));

        assertThat(((Registrant) constructor.invoke(PERCY)).getName()).isEqualTo(PERCY);
        assertThat(((Registrant) constructor.invoke(FRANCESCA)).getName()).isEqualTo(FRANCESCA);
    }

    @Test
    void call_constructor_by_using_basic_reflection() throws Throwable {
        Constructor<Registrant> constructor = Registrant.class.getConstructor(String.class);

        assertThat((constructor.newInstance(PERCY)).getName()).isEqualTo(PERCY);
        assertThat((constructor.newInstance(FRANCESCA)).getName()).isEqualTo(FRANCESCA);
    }

    @Test
    void call_getName_by_using_methodHandles() throws Throwable {
        MethodHandle getName = lookup.findVirtual(Registrant.class, "getName", MethodType.methodType(String.class));

        assertThat(getName.invoke(registrant1)).isEqualTo(PERCY);
        assertThat(getName.invoke(registrant2)).isEqualTo(FRANCESCA);
    }

    @Test
    void call_getName_by_using_basic_reflection() throws Throwable {
        Method getName = Registrant.class.getMethod("getName");

        assertThat(getName.invoke(registrant1)).isEqualTo(PERCY);
        assertThat(getName.invoke(registrant2)).isEqualTo(FRANCESCA);
    }

    @Test
    void call_setName_by_using_methodHandles() throws Throwable {
        MethodHandle setName = lookup.findVirtual(Registrant.class, "setName", MethodType.methodType(void.class, String.class));

        setName.invoke(registrant1, "Nico");
        setName.invoke(registrant2, "Isabella");

        assertThat(registrant1.getName()).isEqualTo("Nico");
        assertThat(registrant2.getName()).isEqualTo("Isabella");
    }

    @Test
    void call_setName_by_using_basic_reflection() throws Throwable {
        Method setName = Registrant.class.getMethod("setName", String.class);

        setName.invoke(registrant1, "Nico");
        setName.invoke(registrant2, "Isabella");

        assertThat(registrant1.getName()).isEqualTo("Nico");
        assertThat(registrant2.getName()).isEqualTo("Isabella");
    }

    @Test
    void call_private_setSavingsAmount_by_using_methodHandles() throws Throwable {
        assertThatThrownBy(() ->
                lookup.findVirtual(Registrant.class, "setSavingsAmount", MethodType.methodType(void.class, long.class)))
                .hasCauseExactlyInstanceOf(IllegalAccessError.class)
                .hasMessage("no such method: com.percyvega.reflectionmethodhandles.Registrant.setSavingsAmount(long)void/invokeVirtual");

        MethodHandles.Lookup privateLookupIn = MethodHandles.privateLookupIn(Registrant.class, lookup);

        MethodHandle setSavingsAmount = privateLookupIn.findVirtual(Registrant.class, "setSavingsAmount", MethodType.methodType(void.class, long.class));

        setSavingsAmount.invoke(registrant1, 100_000);
        setSavingsAmount.invoke(registrant2, 1_000_000);

        assertThat(registrant1.getSavingsAmount()).isEqualTo(100_000);
        assertThat(registrant2.getSavingsAmount()).isEqualTo(1_000_000);
    }

    @Test
    void call_private_setSavingsAmount_by_using_basic_reflection() throws Throwable {
        Method setSavingsAmount = Registrant.class.getDeclaredMethod("setSavingsAmount", long.class);

        setSavingsAmount.setAccessible(true);

        setSavingsAmount.invoke(registrant1, 100_000);
        setSavingsAmount.invoke(registrant2, 1_000_000);

        assertThat(registrant1.getSavingsAmount()).isEqualTo(100_000);
        assertThat(registrant2.getSavingsAmount()).isEqualTo(1_000_000);
    }

}
