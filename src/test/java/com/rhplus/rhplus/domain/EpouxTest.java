package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class EpouxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Epoux.class);
        Epoux epoux1 = new Epoux();
        epoux1.setId(1L);
        Epoux epoux2 = new Epoux();
        epoux2.setId(epoux1.getId());
        assertThat(epoux1).isEqualTo(epoux2);
        epoux2.setId(2L);
        assertThat(epoux1).isNotEqualTo(epoux2);
        epoux1.setId(null);
        assertThat(epoux1).isNotEqualTo(epoux2);
    }
}
