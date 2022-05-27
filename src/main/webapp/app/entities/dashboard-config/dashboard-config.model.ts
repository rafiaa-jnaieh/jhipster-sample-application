import { IUserProfile } from 'app/entities/user-profile/user-profile.model';

export interface IDashboardConfig {
  id?: string;
  margin?: number | null;
  outerMargin?: boolean | null;
  outerMarginTop?: number | null;
  outerMarginRight?: number | null;
  outerMarginBottom?: number | null;
  outerMarginLeft?: number | null;
  useTransformPositioning?: boolean | null;
  mobileBreakpoint?: number | null;
  useBodyForBreakpoint?: boolean | null;
  minCols?: number | null;
  maxCols?: number | null;
  minRows?: number | null;
  maxRows?: number | null;
  maxItemCols?: number | null;
  minItemCols?: number | null;
  maxItemRows?: number | null;
  minItemRows?: number | null;
  maxItemArea?: number | null;
  minItemArea?: number | null;
  defaultItemCols?: number | null;
  defaultItemRows?: number | null;
  fixedColWidth?: number | null;
  fixedRowHeight?: number | null;
  keepFixedHeightInMobile?: boolean | null;
  keepFixedWidthInMobile?: boolean | null;
  scrollSensitivity?: number | null;
  scrollSpeed?: number | null;
  enableEmptyCellClick?: boolean | null;
  enableEmptyCellContextMenu?: boolean | null;
  enableEmptyCellDrop?: boolean | null;
  enableEmptyCellDrag?: boolean | null;
  enableOccupiedCellDrop?: boolean | null;
  emptyCellDragMaxCols?: number | null;
  emptyCellDragMaxRows?: number | null;
  ignoreMarginInRow?: boolean | null;
  draggable?: boolean | null;
  resizable?: boolean | null;
  swap?: boolean | null;
  pushItems?: boolean | null;
  disablePushOnDrag?: boolean | null;
  disablePushOnResize?: boolean | null;
  pushResizeItems?: boolean | null;
  disableWindowResize?: boolean | null;
  disableWarnings?: boolean | null;
  scrollToNewItems?: boolean | null;
  userProfiles?: IUserProfile[] | null;
}

export class DashboardConfig implements IDashboardConfig {
  constructor(
    public id?: string,
    public margin?: number | null,
    public outerMargin?: boolean | null,
    public outerMarginTop?: number | null,
    public outerMarginRight?: number | null,
    public outerMarginBottom?: number | null,
    public outerMarginLeft?: number | null,
    public useTransformPositioning?: boolean | null,
    public mobileBreakpoint?: number | null,
    public useBodyForBreakpoint?: boolean | null,
    public minCols?: number | null,
    public maxCols?: number | null,
    public minRows?: number | null,
    public maxRows?: number | null,
    public maxItemCols?: number | null,
    public minItemCols?: number | null,
    public maxItemRows?: number | null,
    public minItemRows?: number | null,
    public maxItemArea?: number | null,
    public minItemArea?: number | null,
    public defaultItemCols?: number | null,
    public defaultItemRows?: number | null,
    public fixedColWidth?: number | null,
    public fixedRowHeight?: number | null,
    public keepFixedHeightInMobile?: boolean | null,
    public keepFixedWidthInMobile?: boolean | null,
    public scrollSensitivity?: number | null,
    public scrollSpeed?: number | null,
    public enableEmptyCellClick?: boolean | null,
    public enableEmptyCellContextMenu?: boolean | null,
    public enableEmptyCellDrop?: boolean | null,
    public enableEmptyCellDrag?: boolean | null,
    public enableOccupiedCellDrop?: boolean | null,
    public emptyCellDragMaxCols?: number | null,
    public emptyCellDragMaxRows?: number | null,
    public ignoreMarginInRow?: boolean | null,
    public draggable?: boolean | null,
    public resizable?: boolean | null,
    public swap?: boolean | null,
    public pushItems?: boolean | null,
    public disablePushOnDrag?: boolean | null,
    public disablePushOnResize?: boolean | null,
    public pushResizeItems?: boolean | null,
    public disableWindowResize?: boolean | null,
    public disableWarnings?: boolean | null,
    public scrollToNewItems?: boolean | null,
    public userProfiles?: IUserProfile[] | null
  ) {
    this.outerMargin = this.outerMargin ?? false;
    this.useTransformPositioning = this.useTransformPositioning ?? false;
    this.useBodyForBreakpoint = this.useBodyForBreakpoint ?? false;
    this.keepFixedHeightInMobile = this.keepFixedHeightInMobile ?? false;
    this.keepFixedWidthInMobile = this.keepFixedWidthInMobile ?? false;
    this.enableEmptyCellClick = this.enableEmptyCellClick ?? false;
    this.enableEmptyCellContextMenu = this.enableEmptyCellContextMenu ?? false;
    this.enableEmptyCellDrop = this.enableEmptyCellDrop ?? false;
    this.enableEmptyCellDrag = this.enableEmptyCellDrag ?? false;
    this.enableOccupiedCellDrop = this.enableOccupiedCellDrop ?? false;
    this.ignoreMarginInRow = this.ignoreMarginInRow ?? false;
    this.draggable = this.draggable ?? false;
    this.resizable = this.resizable ?? false;
    this.swap = this.swap ?? false;
    this.pushItems = this.pushItems ?? false;
    this.disablePushOnDrag = this.disablePushOnDrag ?? false;
    this.disablePushOnResize = this.disablePushOnResize ?? false;
    this.pushResizeItems = this.pushResizeItems ?? false;
    this.disableWindowResize = this.disableWindowResize ?? false;
    this.disableWarnings = this.disableWarnings ?? false;
    this.scrollToNewItems = this.scrollToNewItems ?? false;
  }
}

export function getDashboardConfigIdentifier(dashboardConfig: IDashboardConfig): string | undefined {
  return dashboardConfig.id;
}
