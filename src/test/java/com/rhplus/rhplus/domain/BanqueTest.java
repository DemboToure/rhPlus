package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class BanqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Banque.class);
        Banque banque1 = new Banque();
        banque1.setId(1L);
        Banque banque2 = new Banque();
        banque2.setId(banque1.getId());
        assertThat(banque1).isEqualTo(banque2);
        banque2.setId(2L);
        assertThat(banque1).isNotEqualTo(banque2);
        banque1.setId(null);
        assertThat(banque1).isNotEqualTo(banque2);
    }
}
