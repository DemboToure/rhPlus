import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RhPlusSharedModule } from 'app/shared/shared.module';
import { EnfantComponent } from './enfant.component';
import { EnfantDetailComponent } from './enfant-detail.component';
import { EnfantUpdateComponent } from './enfant-update.component';
import { EnfantDeleteDialogComponent } from './enfant-delete-dialog.component';
import { enfantRoute } from './enfant.route';

@NgModule({
  imports: [RhPlusSharedModule, RouterModule.forChild(enfantRoute)],
  declarations: [EnfantComponent, EnfantDetailComponent, EnfantUpdateComponent, EnfantDeleteDialogComponent],
  entryComponents: [EnfantDeleteDialogComponent],
})
export class RhPlusEnfantModule {}
