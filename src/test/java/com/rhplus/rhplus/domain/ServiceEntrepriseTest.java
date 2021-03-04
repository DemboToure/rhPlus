package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class ServiceEntrepriseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceEntreprise.class);
        ServiceEntreprise serviceEntreprise1 = new ServiceEntreprise();
        serviceEntreprise1.setId(1L);
        ServiceEntreprise serviceEntreprise2 = new ServiceEntreprise();
        serviceEntreprise2.setId(serviceEntreprise1.getId());
        assertThat(serviceEntreprise1).isEqualTo(serviceEntreprise2);
        serviceEntreprise2.setId(2L);
        assertThat(serviceEntreprise1).isNotEqualTo(serviceEntreprise2);
        serviceEntreprise1.setId(null);
        assertThat(serviceEntreprise1).isNotEqualTo(serviceEntreprise2);
    }
}
