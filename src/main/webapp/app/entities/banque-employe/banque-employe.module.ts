import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RhPlusSharedModule } from 'app/shared/shared.module';
import { BanqueEmployeComponent } from './banque-employe.component';
import { BanqueEmployeDetailComponent } from './banque-employe-detail.component';
import { BanqueEmployeUpdateComponent } from './banque-employe-update.component';
import { BanqueEmployeDeleteDialogComponent } from './banque-employe-delete-dialog.component';
import { banqueEmployeRoute } from './banque-employe.route';

@NgModule({
  imports: [RhPlusSharedModule, RouterModule.forChild(banqueEmployeRoute)],
  declarations: [BanqueEmployeComponent, BanqueEmployeDetailComponent, BanqueEmployeUpdateComponent, BanqueEmployeDeleteDialogComponent],
  entryComponents: [BanqueEmployeDeleteDialogComponent],
})
export class RhPlusBanqueEmployeModule {}
