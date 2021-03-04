import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'app-configuration',
        loadChildren: () => import('./app-configuration/app-configuration.module').then(m => m.RhPlusAppConfigurationModule),
      },
      {
        path: 'societe',
        loadChildren: () => import('./societe/societe.module').then(m => m.RhPlusSocieteModule),
      },
      {
        path: 'employe',
        loadChildren: () => import('./employe/employe.module').then(m => m.RhPlusEmployeModule),
      },
      {
        path: 'enfant',
        loadChildren: () => import('./enfant/enfant.module').then(m => m.RhPlusEnfantModule),
      },
      {
        path: 'epoux',
        loadChildren: () => import('./epoux/epoux.module').then(m => m.RhPlusEpouxModule),
      },
      {
        path: 'banque',
        loadChildren: () => import('./banque/banque.module').then(m => m.RhPlusBanqueModule),
      },
      {
        path: 'banque-employe',
        loadChildren: () => import('./banque-employe/banque-employe.module').then(m => m.RhPlusBanqueEmployeModule),
      },
      {
        path: 'contrat',
        loadChildren: () => import('./contrat/contrat.module').then(m => m.RhPlusContratModule),
      },
      {
        path: 'service-entreprise',
        loadChildren: () => import('./service-entreprise/service-entreprise.module').then(m => m.RhPlusServiceEntrepriseModule),
      },
      {
        path: 'poste',
        loadChildren: () => import('./poste/poste.module').then(m => m.RhPlusPosteModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class RhPlusEntityModule {}
