import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RhPlusSharedModule } from 'app/shared/shared.module';
import { ServiceEntrepriseComponent } from './service-entreprise.component';
import { ServiceEntrepriseDetailComponent } from './service-entreprise-detail.component';
import { ServiceEntrepriseUpdateComponent } from './service-entreprise-update.component';
import { ServiceEntrepriseDeleteDialogComponent } from './service-entreprise-delete-dialog.component';
import { serviceEntrepriseRoute } from './service-entreprise.route';

@NgModule({
  imports: [RhPlusSharedModule, RouterModule.forChild(serviceEntrepriseRoute)],
  declarations: [
    ServiceEntrepriseComponent,
    ServiceEntrepriseDetailComponent,
    ServiceEntrepriseUpdateComponent,
    ServiceEntrepriseDeleteDialogComponent,
  ],
  entryComponents: [ServiceEntrepriseDeleteDialogComponent],
})
export class RhPlusServiceEntrepriseModule {}
