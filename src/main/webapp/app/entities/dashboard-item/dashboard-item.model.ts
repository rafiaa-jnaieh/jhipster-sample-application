import { IUserProfile } from 'app/entities/user-profile/user-profile.model';

export interface IDashboardItem {
  id?: string;
  cols?: number | null;
  rows?: number | null;
  x?: number | null;
  y?: number | null;
  content?: string | null;
  userProfile?: IUserProfile | null;
}

export class DashboardItem implements IDashboardItem {
  constructor(
    public id?: string,
    public cols?: number | null,
    public rows?: number | null,
    public x?: number | null,
    public y?: number | null,
    public content?: string | null,
    public userProfile?: IUserProfile | null
  ) {}
}

export function getDashboardItemIdentifier(dashboardItem: IDashboardItem): string | undefined {
  return dashboardItem.id;
}
