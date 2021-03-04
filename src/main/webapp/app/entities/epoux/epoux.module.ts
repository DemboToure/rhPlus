import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RhPlusSharedModule } from 'app/shared/shared.module';
import { EpouxComponent } from './epoux.component';
import { EpouxDetailComponent } from './epoux-detail.component';
import { EpouxUpdateComponent } from './epoux-update.component';
import { EpouxDeleteDialogComponent } from './epoux-delete-dialog.component';
import { epouxRoute } from './epoux.route';

@NgModule({
  imports: [RhPlusSharedModule, RouterModule.forChild(epouxRoute)],
  declarations: [EpouxComponent, EpouxDetailComponent, EpouxUpdateComponent, EpouxDeleteDialogComponent],
  entryComponents: [EpouxDeleteDialogComponent],
})
export class RhPlusEpouxModule {}
