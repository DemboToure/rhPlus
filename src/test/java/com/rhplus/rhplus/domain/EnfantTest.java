package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class EnfantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Enfant.class);
        Enfant enfant1 = new Enfant();
        enfant1.setId(1L);
        Enfant enfant2 = new Enfant();
        enfant2.setId(enfant1.getId());
        assertThat(enfant1).isEqualTo(enfant2);
        enfant2.setId(2L);
        assertThat(enfant1).isNotEqualTo(enfant2);
        enfant1.setId(null);
        assertThat(enfant1).isNotEqualTo(enfant2);
    }
}
