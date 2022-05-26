import { IUserProfile } from 'app/entities/user-profile/user-profile.model';

export interface IDashboardLayout {
  id?: string;
  minCols?: number | null;
  minRows?: number | null;
  maxRows?: number | null;
  userProfile?: IUserProfile | null;
}

export class DashboardLayout implements IDashboardLayout {
  constructor(
    public id?: string,
    public minCols?: number | null,
    public minRows?: number | null,
    public maxRows?: number | null,
    public userProfile?: IUserProfile | null
  ) {}
}

export function getDashboardLayoutIdentifier(dashboardLayout: IDashboardLayout): string | undefined {
  return dashboardLayout.id;
}
