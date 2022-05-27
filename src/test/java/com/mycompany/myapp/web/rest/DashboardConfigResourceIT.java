package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DashboardConfig;
import com.mycompany.myapp.repository.DashboardConfigRepository;
import com.mycompany.myapp.service.dto.DashboardConfigDTO;
import com.mycompany.myapp.service.mapper.DashboardConfigMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link DashboardConfigResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DashboardConfigResourceIT {

    private static final Integer DEFAULT_MARGIN = 1;
    private static final Integer UPDATED_MARGIN = 2;

    private static final Boolean DEFAULT_OUTER_MARGIN = false;
    private static final Boolean UPDATED_OUTER_MARGIN = true;

    private static final Integer DEFAULT_OUTER_MARGIN_TOP = 1;
    private static final Integer UPDATED_OUTER_MARGIN_TOP = 2;

    private static final Integer DEFAULT_OUTER_MARGIN_RIGHT = 1;
    private static final Integer UPDATED_OUTER_MARGIN_RIGHT = 2;

    private static final Integer DEFAULT_OUTER_MARGIN_BOTTOM = 1;
    private static final Integer UPDATED_OUTER_MARGIN_BOTTOM = 2;

    private static final Integer DEFAULT_OUTER_MARGIN_LEFT = 1;
    private static final Integer UPDATED_OUTER_MARGIN_LEFT = 2;

    private static final Boolean DEFAULT_USE_TRANSFORM_POSITIONING = false;
    private static final Boolean UPDATED_USE_TRANSFORM_POSITIONING = true;

    private static final Integer DEFAULT_MOBILE_BREAKPOINT = 1;
    private static final Integer UPDATED_MOBILE_BREAKPOINT = 2;

    private static final Boolean DEFAULT_USE_BODY_FOR_BREAKPOINT = false;
    private static final Boolean UPDATED_USE_BODY_FOR_BREAKPOINT = true;

    private static final Integer DEFAULT_MIN_COLS = 1;
    private static final Integer UPDATED_MIN_COLS = 2;

    private static final Integer DEFAULT_MAX_COLS = 1;
    private static final Integer UPDATED_MAX_COLS = 2;

    private static final Integer DEFAULT_MIN_ROWS = 1;
    private static final Integer UPDATED_MIN_ROWS = 2;

    private static final Integer DEFAULT_MAX_ROWS = 1;
    private static final Integer UPDATED_MAX_ROWS = 2;

    private static final Integer DEFAULT_MAX_ITEM_COLS = 1;
    private static final Integer UPDATED_MAX_ITEM_COLS = 2;

    private static final Integer DEFAULT_MIN_ITEM_COLS = 1;
    private static final Integer UPDATED_MIN_ITEM_COLS = 2;

    private static final Integer DEFAULT_MAX_ITEM_ROWS = 1;
    private static final Integer UPDATED_MAX_ITEM_ROWS = 2;

    private static final Integer DEFAULT_MIN_ITEM_ROWS = 1;
    private static final Integer UPDATED_MIN_ITEM_ROWS = 2;

    private static final Integer DEFAULT_MAX_ITEM_AREA = 1;
    private static final Integer UPDATED_MAX_ITEM_AREA = 2;

    private static final Integer DEFAULT_MIN_ITEM_AREA = 1;
    private static final Integer UPDATED_MIN_ITEM_AREA = 2;

    private static final Integer DEFAULT_DEFAULT_ITEM_COLS = 1;
    private static final Integer UPDATED_DEFAULT_ITEM_COLS = 2;

    private static final Integer DEFAULT_DEFAULT_ITEM_ROWS = 1;
    private static final Integer UPDATED_DEFAULT_ITEM_ROWS = 2;

    private static final Integer DEFAULT_FIXED_COL_WIDTH = 1;
    private static final Integer UPDATED_FIXED_COL_WIDTH = 2;

    private static final Integer DEFAULT_FIXED_ROW_HEIGHT = 1;
    private static final Integer UPDATED_FIXED_ROW_HEIGHT = 2;

    private static final Boolean DEFAULT_KEEP_FIXED_HEIGHT_IN_MOBILE = false;
    private static final Boolean UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE = true;

    private static final Boolean DEFAULT_KEEP_FIXED_WIDTH_IN_MOBILE = false;
    private static final Boolean UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE = true;

    private static final Integer DEFAULT_SCROLL_SENSITIVITY = 1;
    private static final Integer UPDATED_SCROLL_SENSITIVITY = 2;

    private static final Integer DEFAULT_SCROLL_SPEED = 1;
    private static final Integer UPDATED_SCROLL_SPEED = 2;

    private static final Boolean DEFAULT_ENABLE_EMPTY_CELL_CLICK = false;
    private static final Boolean UPDATED_ENABLE_EMPTY_CELL_CLICK = true;

    private static final Boolean DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU = false;
    private static final Boolean UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU = true;

    private static final Boolean DEFAULT_ENABLE_EMPTY_CELL_DROP = false;
    private static final Boolean UPDATED_ENABLE_EMPTY_CELL_DROP = true;

    private static final Boolean DEFAULT_ENABLE_EMPTY_CELL_DRAG = false;
    private static final Boolean UPDATED_ENABLE_EMPTY_CELL_DRAG = true;

    private static final Boolean DEFAULT_ENABLE_OCCUPIED_CELL_DROP = false;
    private static final Boolean UPDATED_ENABLE_OCCUPIED_CELL_DROP = true;

    private static final Integer DEFAULT_EMPTY_CELL_DRAG_MAX_COLS = 1;
    private static final Integer UPDATED_EMPTY_CELL_DRAG_MAX_COLS = 2;

    private static final Integer DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS = 1;
    private static final Integer UPDATED_EMPTY_CELL_DRAG_MAX_ROWS = 2;

    private static final Boolean DEFAULT_IGNORE_MARGIN_IN_ROW = false;
    private static final Boolean UPDATED_IGNORE_MARGIN_IN_ROW = true;

    private static final Boolean DEFAULT_DRAGGABLE = false;
    private static final Boolean UPDATED_DRAGGABLE = true;

    private static final Boolean DEFAULT_RESIZABLE = false;
    private static final Boolean UPDATED_RESIZABLE = true;

    private static final Boolean DEFAULT_SWAP = false;
    private static final Boolean UPDATED_SWAP = true;

    private static final Boolean DEFAULT_PUSH_ITEMS = false;
    private static final Boolean UPDATED_PUSH_ITEMS = true;

    private static final Boolean DEFAULT_DISABLE_PUSH_ON_DRAG = false;
    private static final Boolean UPDATED_DISABLE_PUSH_ON_DRAG = true;

    private static final Boolean DEFAULT_DISABLE_PUSH_ON_RESIZE = false;
    private static final Boolean UPDATED_DISABLE_PUSH_ON_RESIZE = true;

    private static final Boolean DEFAULT_PUSH_RESIZE_ITEMS = false;
    private static final Boolean UPDATED_PUSH_RESIZE_ITEMS = true;

    private static final Boolean DEFAULT_DISABLE_WINDOW_RESIZE = false;
    private static final Boolean UPDATED_DISABLE_WINDOW_RESIZE = true;

    private static final Boolean DEFAULT_DISABLE_WARNINGS = false;
    private static final Boolean UPDATED_DISABLE_WARNINGS = true;

    private static final Boolean DEFAULT_SCROLL_TO_NEW_ITEMS = false;
    private static final Boolean UPDATED_SCROLL_TO_NEW_ITEMS = true;

    private static final String ENTITY_API_URL = "/api/dashboard-configs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DashboardConfigRepository dashboardConfigRepository;

    @Autowired
    private DashboardConfigMapper dashboardConfigMapper;

    @Autowired
    private MockMvc restDashboardConfigMockMvc;

    private DashboardConfig dashboardConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardConfig createEntity() {
        DashboardConfig dashboardConfig = new DashboardConfig()
            .margin(DEFAULT_MARGIN)
            .outerMargin(DEFAULT_OUTER_MARGIN)
            .outerMarginTop(DEFAULT_OUTER_MARGIN_TOP)
            .outerMarginRight(DEFAULT_OUTER_MARGIN_RIGHT)
            .outerMarginBottom(DEFAULT_OUTER_MARGIN_BOTTOM)
            .outerMarginLeft(DEFAULT_OUTER_MARGIN_LEFT)
            .useTransformPositioning(DEFAULT_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(DEFAULT_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(DEFAULT_USE_BODY_FOR_BREAKPOINT)
            .minCols(DEFAULT_MIN_COLS)
            .maxCols(DEFAULT_MAX_COLS)
            .minRows(DEFAULT_MIN_ROWS)
            .maxRows(DEFAULT_MAX_ROWS)
            .maxItemCols(DEFAULT_MAX_ITEM_COLS)
            .minItemCols(DEFAULT_MIN_ITEM_COLS)
            .maxItemRows(DEFAULT_MAX_ITEM_ROWS)
            .minItemRows(DEFAULT_MIN_ITEM_ROWS)
            .maxItemArea(DEFAULT_MAX_ITEM_AREA)
            .minItemArea(DEFAULT_MIN_ITEM_AREA)
            .defaultItemCols(DEFAULT_DEFAULT_ITEM_COLS)
            .defaultItemRows(DEFAULT_DEFAULT_ITEM_ROWS)
            .fixedColWidth(DEFAULT_FIXED_COL_WIDTH)
            .fixedRowHeight(DEFAULT_FIXED_ROW_HEIGHT)
            .keepFixedHeightInMobile(DEFAULT_KEEP_FIXED_HEIGHT_IN_MOBILE)
            .keepFixedWidthInMobile(DEFAULT_KEEP_FIXED_WIDTH_IN_MOBILE)
            .scrollSensitivity(DEFAULT_SCROLL_SENSITIVITY)
            .scrollSpeed(DEFAULT_SCROLL_SPEED)
            .enableEmptyCellClick(DEFAULT_ENABLE_EMPTY_CELL_CLICK)
            .enableEmptyCellContextMenu(DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU)
            .enableEmptyCellDrop(DEFAULT_ENABLE_EMPTY_CELL_DROP)
            .enableEmptyCellDrag(DEFAULT_ENABLE_EMPTY_CELL_DRAG)
            .enableOccupiedCellDrop(DEFAULT_ENABLE_OCCUPIED_CELL_DROP)
            .emptyCellDragMaxCols(DEFAULT_EMPTY_CELL_DRAG_MAX_COLS)
            .emptyCellDragMaxRows(DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS)
            .ignoreMarginInRow(DEFAULT_IGNORE_MARGIN_IN_ROW)
            .draggable(DEFAULT_DRAGGABLE)
            .resizable(DEFAULT_RESIZABLE)
            .swap(DEFAULT_SWAP)
            .pushItems(DEFAULT_PUSH_ITEMS)
            .disablePushOnDrag(DEFAULT_DISABLE_PUSH_ON_DRAG)
            .disablePushOnResize(DEFAULT_DISABLE_PUSH_ON_RESIZE)
            .pushResizeItems(DEFAULT_PUSH_RESIZE_ITEMS)
            .disableWindowResize(DEFAULT_DISABLE_WINDOW_RESIZE)
            .disableWarnings(DEFAULT_DISABLE_WARNINGS)
            .scrollToNewItems(DEFAULT_SCROLL_TO_NEW_ITEMS);
        return dashboardConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardConfig createUpdatedEntity() {
        DashboardConfig dashboardConfig = new DashboardConfig()
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .outerMarginRight(UPDATED_OUTER_MARGIN_RIGHT)
            .outerMarginBottom(UPDATED_OUTER_MARGIN_BOTTOM)
            .outerMarginLeft(UPDATED_OUTER_MARGIN_LEFT)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .fixedColWidth(UPDATED_FIXED_COL_WIDTH)
            .fixedRowHeight(UPDATED_FIXED_ROW_HEIGHT)
            .keepFixedHeightInMobile(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE)
            .keepFixedWidthInMobile(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE)
            .scrollSensitivity(UPDATED_SCROLL_SENSITIVITY)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .enableEmptyCellClick(UPDATED_ENABLE_EMPTY_CELL_CLICK)
            .enableEmptyCellContextMenu(UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU)
            .enableEmptyCellDrop(UPDATED_ENABLE_EMPTY_CELL_DROP)
            .enableEmptyCellDrag(UPDATED_ENABLE_EMPTY_CELL_DRAG)
            .enableOccupiedCellDrop(UPDATED_ENABLE_OCCUPIED_CELL_DROP)
            .emptyCellDragMaxCols(UPDATED_EMPTY_CELL_DRAG_MAX_COLS)
            .emptyCellDragMaxRows(UPDATED_EMPTY_CELL_DRAG_MAX_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE)
            .swap(UPDATED_SWAP)
            .pushItems(UPDATED_PUSH_ITEMS)
            .disablePushOnDrag(UPDATED_DISABLE_PUSH_ON_DRAG)
            .disablePushOnResize(UPDATED_DISABLE_PUSH_ON_RESIZE)
            .pushResizeItems(UPDATED_PUSH_RESIZE_ITEMS)
            .disableWindowResize(UPDATED_DISABLE_WINDOW_RESIZE)
            .disableWarnings(UPDATED_DISABLE_WARNINGS)
            .scrollToNewItems(UPDATED_SCROLL_TO_NEW_ITEMS);
        return dashboardConfig;
    }

    @BeforeEach
    public void initTest() {
        dashboardConfigRepository.deleteAll();
        dashboardConfig = createEntity();
    }

    @Test
    void createDashboardConfig() throws Exception {
        int databaseSizeBeforeCreate = dashboardConfigRepository.findAll().size();
        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);
        restDashboardConfigMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeCreate + 1);
        DashboardConfig testDashboardConfig = dashboardConfigList.get(dashboardConfigList.size() - 1);
        assertThat(testDashboardConfig.getMargin()).isEqualTo(DEFAULT_MARGIN);
        assertThat(testDashboardConfig.getOuterMargin()).isEqualTo(DEFAULT_OUTER_MARGIN);
        assertThat(testDashboardConfig.getOuterMarginTop()).isEqualTo(DEFAULT_OUTER_MARGIN_TOP);
        assertThat(testDashboardConfig.getOuterMarginRight()).isEqualTo(DEFAULT_OUTER_MARGIN_RIGHT);
        assertThat(testDashboardConfig.getOuterMarginBottom()).isEqualTo(DEFAULT_OUTER_MARGIN_BOTTOM);
        assertThat(testDashboardConfig.getOuterMarginLeft()).isEqualTo(DEFAULT_OUTER_MARGIN_LEFT);
        assertThat(testDashboardConfig.getUseTransformPositioning()).isEqualTo(DEFAULT_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardConfig.getMobileBreakpoint()).isEqualTo(DEFAULT_MOBILE_BREAKPOINT);
        assertThat(testDashboardConfig.getUseBodyForBreakpoint()).isEqualTo(DEFAULT_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardConfig.getMinCols()).isEqualTo(DEFAULT_MIN_COLS);
        assertThat(testDashboardConfig.getMaxCols()).isEqualTo(DEFAULT_MAX_COLS);
        assertThat(testDashboardConfig.getMinRows()).isEqualTo(DEFAULT_MIN_ROWS);
        assertThat(testDashboardConfig.getMaxRows()).isEqualTo(DEFAULT_MAX_ROWS);
        assertThat(testDashboardConfig.getMaxItemCols()).isEqualTo(DEFAULT_MAX_ITEM_COLS);
        assertThat(testDashboardConfig.getMinItemCols()).isEqualTo(DEFAULT_MIN_ITEM_COLS);
        assertThat(testDashboardConfig.getMaxItemRows()).isEqualTo(DEFAULT_MAX_ITEM_ROWS);
        assertThat(testDashboardConfig.getMinItemRows()).isEqualTo(DEFAULT_MIN_ITEM_ROWS);
        assertThat(testDashboardConfig.getMaxItemArea()).isEqualTo(DEFAULT_MAX_ITEM_AREA);
        assertThat(testDashboardConfig.getMinItemArea()).isEqualTo(DEFAULT_MIN_ITEM_AREA);
        assertThat(testDashboardConfig.getDefaultItemCols()).isEqualTo(DEFAULT_DEFAULT_ITEM_COLS);
        assertThat(testDashboardConfig.getDefaultItemRows()).isEqualTo(DEFAULT_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardConfig.getFixedColWidth()).isEqualTo(DEFAULT_FIXED_COL_WIDTH);
        assertThat(testDashboardConfig.getFixedRowHeight()).isEqualTo(DEFAULT_FIXED_ROW_HEIGHT);
        assertThat(testDashboardConfig.getKeepFixedHeightInMobile()).isEqualTo(DEFAULT_KEEP_FIXED_HEIGHT_IN_MOBILE);
        assertThat(testDashboardConfig.getKeepFixedWidthInMobile()).isEqualTo(DEFAULT_KEEP_FIXED_WIDTH_IN_MOBILE);
        assertThat(testDashboardConfig.getScrollSensitivity()).isEqualTo(DEFAULT_SCROLL_SENSITIVITY);
        assertThat(testDashboardConfig.getScrollSpeed()).isEqualTo(DEFAULT_SCROLL_SPEED);
        assertThat(testDashboardConfig.getEnableEmptyCellClick()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_CLICK);
        assertThat(testDashboardConfig.getEnableEmptyCellContextMenu()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU);
        assertThat(testDashboardConfig.getEnableEmptyCellDrop()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_DROP);
        assertThat(testDashboardConfig.getEnableEmptyCellDrag()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_DRAG);
        assertThat(testDashboardConfig.getEnableOccupiedCellDrop()).isEqualTo(DEFAULT_ENABLE_OCCUPIED_CELL_DROP);
        assertThat(testDashboardConfig.getEmptyCellDragMaxCols()).isEqualTo(DEFAULT_EMPTY_CELL_DRAG_MAX_COLS);
        assertThat(testDashboardConfig.getEmptyCellDragMaxRows()).isEqualTo(DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS);
        assertThat(testDashboardConfig.getIgnoreMarginInRow()).isEqualTo(DEFAULT_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardConfig.getDraggable()).isEqualTo(DEFAULT_DRAGGABLE);
        assertThat(testDashboardConfig.getResizable()).isEqualTo(DEFAULT_RESIZABLE);
        assertThat(testDashboardConfig.getSwap()).isEqualTo(DEFAULT_SWAP);
        assertThat(testDashboardConfig.getPushItems()).isEqualTo(DEFAULT_PUSH_ITEMS);
        assertThat(testDashboardConfig.getDisablePushOnDrag()).isEqualTo(DEFAULT_DISABLE_PUSH_ON_DRAG);
        assertThat(testDashboardConfig.getDisablePushOnResize()).isEqualTo(DEFAULT_DISABLE_PUSH_ON_RESIZE);
        assertThat(testDashboardConfig.getPushResizeItems()).isEqualTo(DEFAULT_PUSH_RESIZE_ITEMS);
        assertThat(testDashboardConfig.getDisableWindowResize()).isEqualTo(DEFAULT_DISABLE_WINDOW_RESIZE);
        assertThat(testDashboardConfig.getDisableWarnings()).isEqualTo(DEFAULT_DISABLE_WARNINGS);
        assertThat(testDashboardConfig.getScrollToNewItems()).isEqualTo(DEFAULT_SCROLL_TO_NEW_ITEMS);
    }

    @Test
    void createDashboardConfigWithExistingId() throws Exception {
        // Create the DashboardConfig with an existing ID
        dashboardConfig.setId("existing_id");
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        int databaseSizeBeforeCreate = dashboardConfigRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDashboardConfigMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDashboardConfigs() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        // Get all the dashboardConfigList
        restDashboardConfigMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dashboardConfig.getId())))
            .andExpect(jsonPath("$.[*].margin").value(hasItem(DEFAULT_MARGIN)))
            .andExpect(jsonPath("$.[*].outerMargin").value(hasItem(DEFAULT_OUTER_MARGIN.booleanValue())))
            .andExpect(jsonPath("$.[*].outerMarginTop").value(hasItem(DEFAULT_OUTER_MARGIN_TOP)))
            .andExpect(jsonPath("$.[*].outerMarginRight").value(hasItem(DEFAULT_OUTER_MARGIN_RIGHT)))
            .andExpect(jsonPath("$.[*].outerMarginBottom").value(hasItem(DEFAULT_OUTER_MARGIN_BOTTOM)))
            .andExpect(jsonPath("$.[*].outerMarginLeft").value(hasItem(DEFAULT_OUTER_MARGIN_LEFT)))
            .andExpect(jsonPath("$.[*].useTransformPositioning").value(hasItem(DEFAULT_USE_TRANSFORM_POSITIONING.booleanValue())))
            .andExpect(jsonPath("$.[*].mobileBreakpoint").value(hasItem(DEFAULT_MOBILE_BREAKPOINT)))
            .andExpect(jsonPath("$.[*].useBodyForBreakpoint").value(hasItem(DEFAULT_USE_BODY_FOR_BREAKPOINT.booleanValue())))
            .andExpect(jsonPath("$.[*].minCols").value(hasItem(DEFAULT_MIN_COLS)))
            .andExpect(jsonPath("$.[*].maxCols").value(hasItem(DEFAULT_MAX_COLS)))
            .andExpect(jsonPath("$.[*].minRows").value(hasItem(DEFAULT_MIN_ROWS)))
            .andExpect(jsonPath("$.[*].maxRows").value(hasItem(DEFAULT_MAX_ROWS)))
            .andExpect(jsonPath("$.[*].maxItemCols").value(hasItem(DEFAULT_MAX_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].minItemCols").value(hasItem(DEFAULT_MIN_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].maxItemRows").value(hasItem(DEFAULT_MAX_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].minItemRows").value(hasItem(DEFAULT_MIN_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].maxItemArea").value(hasItem(DEFAULT_MAX_ITEM_AREA)))
            .andExpect(jsonPath("$.[*].minItemArea").value(hasItem(DEFAULT_MIN_ITEM_AREA)))
            .andExpect(jsonPath("$.[*].defaultItemCols").value(hasItem(DEFAULT_DEFAULT_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].defaultItemRows").value(hasItem(DEFAULT_DEFAULT_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].fixedColWidth").value(hasItem(DEFAULT_FIXED_COL_WIDTH)))
            .andExpect(jsonPath("$.[*].fixedRowHeight").value(hasItem(DEFAULT_FIXED_ROW_HEIGHT)))
            .andExpect(jsonPath("$.[*].keepFixedHeightInMobile").value(hasItem(DEFAULT_KEEP_FIXED_HEIGHT_IN_MOBILE.booleanValue())))
            .andExpect(jsonPath("$.[*].keepFixedWidthInMobile").value(hasItem(DEFAULT_KEEP_FIXED_WIDTH_IN_MOBILE.booleanValue())))
            .andExpect(jsonPath("$.[*].scrollSensitivity").value(hasItem(DEFAULT_SCROLL_SENSITIVITY)))
            .andExpect(jsonPath("$.[*].scrollSpeed").value(hasItem(DEFAULT_SCROLL_SPEED)))
            .andExpect(jsonPath("$.[*].enableEmptyCellClick").value(hasItem(DEFAULT_ENABLE_EMPTY_CELL_CLICK.booleanValue())))
            .andExpect(jsonPath("$.[*].enableEmptyCellContextMenu").value(hasItem(DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU.booleanValue())))
            .andExpect(jsonPath("$.[*].enableEmptyCellDrop").value(hasItem(DEFAULT_ENABLE_EMPTY_CELL_DROP.booleanValue())))
            .andExpect(jsonPath("$.[*].enableEmptyCellDrag").value(hasItem(DEFAULT_ENABLE_EMPTY_CELL_DRAG.booleanValue())))
            .andExpect(jsonPath("$.[*].enableOccupiedCellDrop").value(hasItem(DEFAULT_ENABLE_OCCUPIED_CELL_DROP.booleanValue())))
            .andExpect(jsonPath("$.[*].emptyCellDragMaxCols").value(hasItem(DEFAULT_EMPTY_CELL_DRAG_MAX_COLS)))
            .andExpect(jsonPath("$.[*].emptyCellDragMaxRows").value(hasItem(DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS)))
            .andExpect(jsonPath("$.[*].ignoreMarginInRow").value(hasItem(DEFAULT_IGNORE_MARGIN_IN_ROW.booleanValue())))
            .andExpect(jsonPath("$.[*].draggable").value(hasItem(DEFAULT_DRAGGABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].resizable").value(hasItem(DEFAULT_RESIZABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].swap").value(hasItem(DEFAULT_SWAP.booleanValue())))
            .andExpect(jsonPath("$.[*].pushItems").value(hasItem(DEFAULT_PUSH_ITEMS.booleanValue())))
            .andExpect(jsonPath("$.[*].disablePushOnDrag").value(hasItem(DEFAULT_DISABLE_PUSH_ON_DRAG.booleanValue())))
            .andExpect(jsonPath("$.[*].disablePushOnResize").value(hasItem(DEFAULT_DISABLE_PUSH_ON_RESIZE.booleanValue())))
            .andExpect(jsonPath("$.[*].pushResizeItems").value(hasItem(DEFAULT_PUSH_RESIZE_ITEMS.booleanValue())))
            .andExpect(jsonPath("$.[*].disableWindowResize").value(hasItem(DEFAULT_DISABLE_WINDOW_RESIZE.booleanValue())))
            .andExpect(jsonPath("$.[*].disableWarnings").value(hasItem(DEFAULT_DISABLE_WARNINGS.booleanValue())))
            .andExpect(jsonPath("$.[*].scrollToNewItems").value(hasItem(DEFAULT_SCROLL_TO_NEW_ITEMS.booleanValue())));
    }

    @Test
    void getDashboardConfig() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        // Get the dashboardConfig
        restDashboardConfigMockMvc
            .perform(get(ENTITY_API_URL_ID, dashboardConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dashboardConfig.getId()))
            .andExpect(jsonPath("$.margin").value(DEFAULT_MARGIN))
            .andExpect(jsonPath("$.outerMargin").value(DEFAULT_OUTER_MARGIN.booleanValue()))
            .andExpect(jsonPath("$.outerMarginTop").value(DEFAULT_OUTER_MARGIN_TOP))
            .andExpect(jsonPath("$.outerMarginRight").value(DEFAULT_OUTER_MARGIN_RIGHT))
            .andExpect(jsonPath("$.outerMarginBottom").value(DEFAULT_OUTER_MARGIN_BOTTOM))
            .andExpect(jsonPath("$.outerMarginLeft").value(DEFAULT_OUTER_MARGIN_LEFT))
            .andExpect(jsonPath("$.useTransformPositioning").value(DEFAULT_USE_TRANSFORM_POSITIONING.booleanValue()))
            .andExpect(jsonPath("$.mobileBreakpoint").value(DEFAULT_MOBILE_BREAKPOINT))
            .andExpect(jsonPath("$.useBodyForBreakpoint").value(DEFAULT_USE_BODY_FOR_BREAKPOINT.booleanValue()))
            .andExpect(jsonPath("$.minCols").value(DEFAULT_MIN_COLS))
            .andExpect(jsonPath("$.maxCols").value(DEFAULT_MAX_COLS))
            .andExpect(jsonPath("$.minRows").value(DEFAULT_MIN_ROWS))
            .andExpect(jsonPath("$.maxRows").value(DEFAULT_MAX_ROWS))
            .andExpect(jsonPath("$.maxItemCols").value(DEFAULT_MAX_ITEM_COLS))
            .andExpect(jsonPath("$.minItemCols").value(DEFAULT_MIN_ITEM_COLS))
            .andExpect(jsonPath("$.maxItemRows").value(DEFAULT_MAX_ITEM_ROWS))
            .andExpect(jsonPath("$.minItemRows").value(DEFAULT_MIN_ITEM_ROWS))
            .andExpect(jsonPath("$.maxItemArea").value(DEFAULT_MAX_ITEM_AREA))
            .andExpect(jsonPath("$.minItemArea").value(DEFAULT_MIN_ITEM_AREA))
            .andExpect(jsonPath("$.defaultItemCols").value(DEFAULT_DEFAULT_ITEM_COLS))
            .andExpect(jsonPath("$.defaultItemRows").value(DEFAULT_DEFAULT_ITEM_ROWS))
            .andExpect(jsonPath("$.fixedColWidth").value(DEFAULT_FIXED_COL_WIDTH))
            .andExpect(jsonPath("$.fixedRowHeight").value(DEFAULT_FIXED_ROW_HEIGHT))
            .andExpect(jsonPath("$.keepFixedHeightInMobile").value(DEFAULT_KEEP_FIXED_HEIGHT_IN_MOBILE.booleanValue()))
            .andExpect(jsonPath("$.keepFixedWidthInMobile").value(DEFAULT_KEEP_FIXED_WIDTH_IN_MOBILE.booleanValue()))
            .andExpect(jsonPath("$.scrollSensitivity").value(DEFAULT_SCROLL_SENSITIVITY))
            .andExpect(jsonPath("$.scrollSpeed").value(DEFAULT_SCROLL_SPEED))
            .andExpect(jsonPath("$.enableEmptyCellClick").value(DEFAULT_ENABLE_EMPTY_CELL_CLICK.booleanValue()))
            .andExpect(jsonPath("$.enableEmptyCellContextMenu").value(DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU.booleanValue()))
            .andExpect(jsonPath("$.enableEmptyCellDrop").value(DEFAULT_ENABLE_EMPTY_CELL_DROP.booleanValue()))
            .andExpect(jsonPath("$.enableEmptyCellDrag").value(DEFAULT_ENABLE_EMPTY_CELL_DRAG.booleanValue()))
            .andExpect(jsonPath("$.enableOccupiedCellDrop").value(DEFAULT_ENABLE_OCCUPIED_CELL_DROP.booleanValue()))
            .andExpect(jsonPath("$.emptyCellDragMaxCols").value(DEFAULT_EMPTY_CELL_DRAG_MAX_COLS))
            .andExpect(jsonPath("$.emptyCellDragMaxRows").value(DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS))
            .andExpect(jsonPath("$.ignoreMarginInRow").value(DEFAULT_IGNORE_MARGIN_IN_ROW.booleanValue()))
            .andExpect(jsonPath("$.draggable").value(DEFAULT_DRAGGABLE.booleanValue()))
            .andExpect(jsonPath("$.resizable").value(DEFAULT_RESIZABLE.booleanValue()))
            .andExpect(jsonPath("$.swap").value(DEFAULT_SWAP.booleanValue()))
            .andExpect(jsonPath("$.pushItems").value(DEFAULT_PUSH_ITEMS.booleanValue()))
            .andExpect(jsonPath("$.disablePushOnDrag").value(DEFAULT_DISABLE_PUSH_ON_DRAG.booleanValue()))
            .andExpect(jsonPath("$.disablePushOnResize").value(DEFAULT_DISABLE_PUSH_ON_RESIZE.booleanValue()))
            .andExpect(jsonPath("$.pushResizeItems").value(DEFAULT_PUSH_RESIZE_ITEMS.booleanValue()))
            .andExpect(jsonPath("$.disableWindowResize").value(DEFAULT_DISABLE_WINDOW_RESIZE.booleanValue()))
            .andExpect(jsonPath("$.disableWarnings").value(DEFAULT_DISABLE_WARNINGS.booleanValue()))
            .andExpect(jsonPath("$.scrollToNewItems").value(DEFAULT_SCROLL_TO_NEW_ITEMS.booleanValue()));
    }

    @Test
    void getNonExistingDashboardConfig() throws Exception {
        // Get the dashboardConfig
        restDashboardConfigMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewDashboardConfig() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();

        // Update the dashboardConfig
        DashboardConfig updatedDashboardConfig = dashboardConfigRepository.findById(dashboardConfig.getId()).get();
        updatedDashboardConfig
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .outerMarginRight(UPDATED_OUTER_MARGIN_RIGHT)
            .outerMarginBottom(UPDATED_OUTER_MARGIN_BOTTOM)
            .outerMarginLeft(UPDATED_OUTER_MARGIN_LEFT)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .fixedColWidth(UPDATED_FIXED_COL_WIDTH)
            .fixedRowHeight(UPDATED_FIXED_ROW_HEIGHT)
            .keepFixedHeightInMobile(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE)
            .keepFixedWidthInMobile(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE)
            .scrollSensitivity(UPDATED_SCROLL_SENSITIVITY)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .enableEmptyCellClick(UPDATED_ENABLE_EMPTY_CELL_CLICK)
            .enableEmptyCellContextMenu(UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU)
            .enableEmptyCellDrop(UPDATED_ENABLE_EMPTY_CELL_DROP)
            .enableEmptyCellDrag(UPDATED_ENABLE_EMPTY_CELL_DRAG)
            .enableOccupiedCellDrop(UPDATED_ENABLE_OCCUPIED_CELL_DROP)
            .emptyCellDragMaxCols(UPDATED_EMPTY_CELL_DRAG_MAX_COLS)
            .emptyCellDragMaxRows(UPDATED_EMPTY_CELL_DRAG_MAX_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE)
            .swap(UPDATED_SWAP)
            .pushItems(UPDATED_PUSH_ITEMS)
            .disablePushOnDrag(UPDATED_DISABLE_PUSH_ON_DRAG)
            .disablePushOnResize(UPDATED_DISABLE_PUSH_ON_RESIZE)
            .pushResizeItems(UPDATED_PUSH_RESIZE_ITEMS)
            .disableWindowResize(UPDATED_DISABLE_WINDOW_RESIZE)
            .disableWarnings(UPDATED_DISABLE_WARNINGS)
            .scrollToNewItems(UPDATED_SCROLL_TO_NEW_ITEMS);
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(updatedDashboardConfig);

        restDashboardConfigMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardConfigDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isOk());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
        DashboardConfig testDashboardConfig = dashboardConfigList.get(dashboardConfigList.size() - 1);
        assertThat(testDashboardConfig.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardConfig.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardConfig.getOuterMarginTop()).isEqualTo(UPDATED_OUTER_MARGIN_TOP);
        assertThat(testDashboardConfig.getOuterMarginRight()).isEqualTo(UPDATED_OUTER_MARGIN_RIGHT);
        assertThat(testDashboardConfig.getOuterMarginBottom()).isEqualTo(UPDATED_OUTER_MARGIN_BOTTOM);
        assertThat(testDashboardConfig.getOuterMarginLeft()).isEqualTo(UPDATED_OUTER_MARGIN_LEFT);
        assertThat(testDashboardConfig.getUseTransformPositioning()).isEqualTo(UPDATED_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardConfig.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardConfig.getUseBodyForBreakpoint()).isEqualTo(UPDATED_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardConfig.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardConfig.getMaxCols()).isEqualTo(UPDATED_MAX_COLS);
        assertThat(testDashboardConfig.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardConfig.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
        assertThat(testDashboardConfig.getMaxItemCols()).isEqualTo(UPDATED_MAX_ITEM_COLS);
        assertThat(testDashboardConfig.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardConfig.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardConfig.getMinItemRows()).isEqualTo(UPDATED_MIN_ITEM_ROWS);
        assertThat(testDashboardConfig.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardConfig.getMinItemArea()).isEqualTo(UPDATED_MIN_ITEM_AREA);
        assertThat(testDashboardConfig.getDefaultItemCols()).isEqualTo(UPDATED_DEFAULT_ITEM_COLS);
        assertThat(testDashboardConfig.getDefaultItemRows()).isEqualTo(UPDATED_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardConfig.getFixedColWidth()).isEqualTo(UPDATED_FIXED_COL_WIDTH);
        assertThat(testDashboardConfig.getFixedRowHeight()).isEqualTo(UPDATED_FIXED_ROW_HEIGHT);
        assertThat(testDashboardConfig.getKeepFixedHeightInMobile()).isEqualTo(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE);
        assertThat(testDashboardConfig.getKeepFixedWidthInMobile()).isEqualTo(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE);
        assertThat(testDashboardConfig.getScrollSensitivity()).isEqualTo(UPDATED_SCROLL_SENSITIVITY);
        assertThat(testDashboardConfig.getScrollSpeed()).isEqualTo(UPDATED_SCROLL_SPEED);
        assertThat(testDashboardConfig.getEnableEmptyCellClick()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_CLICK);
        assertThat(testDashboardConfig.getEnableEmptyCellContextMenu()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU);
        assertThat(testDashboardConfig.getEnableEmptyCellDrop()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_DROP);
        assertThat(testDashboardConfig.getEnableEmptyCellDrag()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_DRAG);
        assertThat(testDashboardConfig.getEnableOccupiedCellDrop()).isEqualTo(UPDATED_ENABLE_OCCUPIED_CELL_DROP);
        assertThat(testDashboardConfig.getEmptyCellDragMaxCols()).isEqualTo(UPDATED_EMPTY_CELL_DRAG_MAX_COLS);
        assertThat(testDashboardConfig.getEmptyCellDragMaxRows()).isEqualTo(UPDATED_EMPTY_CELL_DRAG_MAX_ROWS);
        assertThat(testDashboardConfig.getIgnoreMarginInRow()).isEqualTo(UPDATED_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardConfig.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardConfig.getResizable()).isEqualTo(UPDATED_RESIZABLE);
        assertThat(testDashboardConfig.getSwap()).isEqualTo(UPDATED_SWAP);
        assertThat(testDashboardConfig.getPushItems()).isEqualTo(UPDATED_PUSH_ITEMS);
        assertThat(testDashboardConfig.getDisablePushOnDrag()).isEqualTo(UPDATED_DISABLE_PUSH_ON_DRAG);
        assertThat(testDashboardConfig.getDisablePushOnResize()).isEqualTo(UPDATED_DISABLE_PUSH_ON_RESIZE);
        assertThat(testDashboardConfig.getPushResizeItems()).isEqualTo(UPDATED_PUSH_RESIZE_ITEMS);
        assertThat(testDashboardConfig.getDisableWindowResize()).isEqualTo(UPDATED_DISABLE_WINDOW_RESIZE);
        assertThat(testDashboardConfig.getDisableWarnings()).isEqualTo(UPDATED_DISABLE_WARNINGS);
        assertThat(testDashboardConfig.getScrollToNewItems()).isEqualTo(UPDATED_SCROLL_TO_NEW_ITEMS);
    }

    @Test
    void putNonExistingDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardConfigDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDashboardConfigWithPatch() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();

        // Update the dashboardConfig using partial update
        DashboardConfig partialUpdatedDashboardConfig = new DashboardConfig();
        partialUpdatedDashboardConfig.setId(dashboardConfig.getId());

        partialUpdatedDashboardConfig
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginRight(UPDATED_OUTER_MARGIN_RIGHT)
            .outerMarginBottom(UPDATED_OUTER_MARGIN_BOTTOM)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minRows(UPDATED_MIN_ROWS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .fixedRowHeight(UPDATED_FIXED_ROW_HEIGHT)
            .keepFixedHeightInMobile(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE)
            .keepFixedWidthInMobile(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .enableOccupiedCellDrop(UPDATED_ENABLE_OCCUPIED_CELL_DROP)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .pushItems(UPDATED_PUSH_ITEMS)
            .pushResizeItems(UPDATED_PUSH_RESIZE_ITEMS)
            .disableWindowResize(UPDATED_DISABLE_WINDOW_RESIZE)
            .disableWarnings(UPDATED_DISABLE_WARNINGS);

        restDashboardConfigMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardConfig.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardConfig))
            )
            .andExpect(status().isOk());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
        DashboardConfig testDashboardConfig = dashboardConfigList.get(dashboardConfigList.size() - 1);
        assertThat(testDashboardConfig.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardConfig.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardConfig.getOuterMarginTop()).isEqualTo(DEFAULT_OUTER_MARGIN_TOP);
        assertThat(testDashboardConfig.getOuterMarginRight()).isEqualTo(UPDATED_OUTER_MARGIN_RIGHT);
        assertThat(testDashboardConfig.getOuterMarginBottom()).isEqualTo(UPDATED_OUTER_MARGIN_BOTTOM);
        assertThat(testDashboardConfig.getOuterMarginLeft()).isEqualTo(DEFAULT_OUTER_MARGIN_LEFT);
        assertThat(testDashboardConfig.getUseTransformPositioning()).isEqualTo(UPDATED_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardConfig.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardConfig.getUseBodyForBreakpoint()).isEqualTo(UPDATED_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardConfig.getMinCols()).isEqualTo(DEFAULT_MIN_COLS);
        assertThat(testDashboardConfig.getMaxCols()).isEqualTo(DEFAULT_MAX_COLS);
        assertThat(testDashboardConfig.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardConfig.getMaxRows()).isEqualTo(DEFAULT_MAX_ROWS);
        assertThat(testDashboardConfig.getMaxItemCols()).isEqualTo(DEFAULT_MAX_ITEM_COLS);
        assertThat(testDashboardConfig.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardConfig.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardConfig.getMinItemRows()).isEqualTo(DEFAULT_MIN_ITEM_ROWS);
        assertThat(testDashboardConfig.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardConfig.getMinItemArea()).isEqualTo(DEFAULT_MIN_ITEM_AREA);
        assertThat(testDashboardConfig.getDefaultItemCols()).isEqualTo(UPDATED_DEFAULT_ITEM_COLS);
        assertThat(testDashboardConfig.getDefaultItemRows()).isEqualTo(UPDATED_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardConfig.getFixedColWidth()).isEqualTo(DEFAULT_FIXED_COL_WIDTH);
        assertThat(testDashboardConfig.getFixedRowHeight()).isEqualTo(UPDATED_FIXED_ROW_HEIGHT);
        assertThat(testDashboardConfig.getKeepFixedHeightInMobile()).isEqualTo(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE);
        assertThat(testDashboardConfig.getKeepFixedWidthInMobile()).isEqualTo(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE);
        assertThat(testDashboardConfig.getScrollSensitivity()).isEqualTo(DEFAULT_SCROLL_SENSITIVITY);
        assertThat(testDashboardConfig.getScrollSpeed()).isEqualTo(UPDATED_SCROLL_SPEED);
        assertThat(testDashboardConfig.getEnableEmptyCellClick()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_CLICK);
        assertThat(testDashboardConfig.getEnableEmptyCellContextMenu()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_CONTEXT_MENU);
        assertThat(testDashboardConfig.getEnableEmptyCellDrop()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_DROP);
        assertThat(testDashboardConfig.getEnableEmptyCellDrag()).isEqualTo(DEFAULT_ENABLE_EMPTY_CELL_DRAG);
        assertThat(testDashboardConfig.getEnableOccupiedCellDrop()).isEqualTo(UPDATED_ENABLE_OCCUPIED_CELL_DROP);
        assertThat(testDashboardConfig.getEmptyCellDragMaxCols()).isEqualTo(DEFAULT_EMPTY_CELL_DRAG_MAX_COLS);
        assertThat(testDashboardConfig.getEmptyCellDragMaxRows()).isEqualTo(DEFAULT_EMPTY_CELL_DRAG_MAX_ROWS);
        assertThat(testDashboardConfig.getIgnoreMarginInRow()).isEqualTo(UPDATED_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardConfig.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardConfig.getResizable()).isEqualTo(DEFAULT_RESIZABLE);
        assertThat(testDashboardConfig.getSwap()).isEqualTo(DEFAULT_SWAP);
        assertThat(testDashboardConfig.getPushItems()).isEqualTo(UPDATED_PUSH_ITEMS);
        assertThat(testDashboardConfig.getDisablePushOnDrag()).isEqualTo(DEFAULT_DISABLE_PUSH_ON_DRAG);
        assertThat(testDashboardConfig.getDisablePushOnResize()).isEqualTo(DEFAULT_DISABLE_PUSH_ON_RESIZE);
        assertThat(testDashboardConfig.getPushResizeItems()).isEqualTo(UPDATED_PUSH_RESIZE_ITEMS);
        assertThat(testDashboardConfig.getDisableWindowResize()).isEqualTo(UPDATED_DISABLE_WINDOW_RESIZE);
        assertThat(testDashboardConfig.getDisableWarnings()).isEqualTo(UPDATED_DISABLE_WARNINGS);
        assertThat(testDashboardConfig.getScrollToNewItems()).isEqualTo(DEFAULT_SCROLL_TO_NEW_ITEMS);
    }

    @Test
    void fullUpdateDashboardConfigWithPatch() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();

        // Update the dashboardConfig using partial update
        DashboardConfig partialUpdatedDashboardConfig = new DashboardConfig();
        partialUpdatedDashboardConfig.setId(dashboardConfig.getId());

        partialUpdatedDashboardConfig
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .outerMarginRight(UPDATED_OUTER_MARGIN_RIGHT)
            .outerMarginBottom(UPDATED_OUTER_MARGIN_BOTTOM)
            .outerMarginLeft(UPDATED_OUTER_MARGIN_LEFT)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .fixedColWidth(UPDATED_FIXED_COL_WIDTH)
            .fixedRowHeight(UPDATED_FIXED_ROW_HEIGHT)
            .keepFixedHeightInMobile(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE)
            .keepFixedWidthInMobile(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE)
            .scrollSensitivity(UPDATED_SCROLL_SENSITIVITY)
            .scrollSpeed(UPDATED_SCROLL_SPEED)
            .enableEmptyCellClick(UPDATED_ENABLE_EMPTY_CELL_CLICK)
            .enableEmptyCellContextMenu(UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU)
            .enableEmptyCellDrop(UPDATED_ENABLE_EMPTY_CELL_DROP)
            .enableEmptyCellDrag(UPDATED_ENABLE_EMPTY_CELL_DRAG)
            .enableOccupiedCellDrop(UPDATED_ENABLE_OCCUPIED_CELL_DROP)
            .emptyCellDragMaxCols(UPDATED_EMPTY_CELL_DRAG_MAX_COLS)
            .emptyCellDragMaxRows(UPDATED_EMPTY_CELL_DRAG_MAX_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE)
            .swap(UPDATED_SWAP)
            .pushItems(UPDATED_PUSH_ITEMS)
            .disablePushOnDrag(UPDATED_DISABLE_PUSH_ON_DRAG)
            .disablePushOnResize(UPDATED_DISABLE_PUSH_ON_RESIZE)
            .pushResizeItems(UPDATED_PUSH_RESIZE_ITEMS)
            .disableWindowResize(UPDATED_DISABLE_WINDOW_RESIZE)
            .disableWarnings(UPDATED_DISABLE_WARNINGS)
            .scrollToNewItems(UPDATED_SCROLL_TO_NEW_ITEMS);

        restDashboardConfigMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardConfig.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardConfig))
            )
            .andExpect(status().isOk());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
        DashboardConfig testDashboardConfig = dashboardConfigList.get(dashboardConfigList.size() - 1);
        assertThat(testDashboardConfig.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardConfig.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardConfig.getOuterMarginTop()).isEqualTo(UPDATED_OUTER_MARGIN_TOP);
        assertThat(testDashboardConfig.getOuterMarginRight()).isEqualTo(UPDATED_OUTER_MARGIN_RIGHT);
        assertThat(testDashboardConfig.getOuterMarginBottom()).isEqualTo(UPDATED_OUTER_MARGIN_BOTTOM);
        assertThat(testDashboardConfig.getOuterMarginLeft()).isEqualTo(UPDATED_OUTER_MARGIN_LEFT);
        assertThat(testDashboardConfig.getUseTransformPositioning()).isEqualTo(UPDATED_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardConfig.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardConfig.getUseBodyForBreakpoint()).isEqualTo(UPDATED_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardConfig.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardConfig.getMaxCols()).isEqualTo(UPDATED_MAX_COLS);
        assertThat(testDashboardConfig.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardConfig.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
        assertThat(testDashboardConfig.getMaxItemCols()).isEqualTo(UPDATED_MAX_ITEM_COLS);
        assertThat(testDashboardConfig.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardConfig.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardConfig.getMinItemRows()).isEqualTo(UPDATED_MIN_ITEM_ROWS);
        assertThat(testDashboardConfig.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardConfig.getMinItemArea()).isEqualTo(UPDATED_MIN_ITEM_AREA);
        assertThat(testDashboardConfig.getDefaultItemCols()).isEqualTo(UPDATED_DEFAULT_ITEM_COLS);
        assertThat(testDashboardConfig.getDefaultItemRows()).isEqualTo(UPDATED_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardConfig.getFixedColWidth()).isEqualTo(UPDATED_FIXED_COL_WIDTH);
        assertThat(testDashboardConfig.getFixedRowHeight()).isEqualTo(UPDATED_FIXED_ROW_HEIGHT);
        assertThat(testDashboardConfig.getKeepFixedHeightInMobile()).isEqualTo(UPDATED_KEEP_FIXED_HEIGHT_IN_MOBILE);
        assertThat(testDashboardConfig.getKeepFixedWidthInMobile()).isEqualTo(UPDATED_KEEP_FIXED_WIDTH_IN_MOBILE);
        assertThat(testDashboardConfig.getScrollSensitivity()).isEqualTo(UPDATED_SCROLL_SENSITIVITY);
        assertThat(testDashboardConfig.getScrollSpeed()).isEqualTo(UPDATED_SCROLL_SPEED);
        assertThat(testDashboardConfig.getEnableEmptyCellClick()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_CLICK);
        assertThat(testDashboardConfig.getEnableEmptyCellContextMenu()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_CONTEXT_MENU);
        assertThat(testDashboardConfig.getEnableEmptyCellDrop()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_DROP);
        assertThat(testDashboardConfig.getEnableEmptyCellDrag()).isEqualTo(UPDATED_ENABLE_EMPTY_CELL_DRAG);
        assertThat(testDashboardConfig.getEnableOccupiedCellDrop()).isEqualTo(UPDATED_ENABLE_OCCUPIED_CELL_DROP);
        assertThat(testDashboardConfig.getEmptyCellDragMaxCols()).isEqualTo(UPDATED_EMPTY_CELL_DRAG_MAX_COLS);
        assertThat(testDashboardConfig.getEmptyCellDragMaxRows()).isEqualTo(UPDATED_EMPTY_CELL_DRAG_MAX_ROWS);
        assertThat(testDashboardConfig.getIgnoreMarginInRow()).isEqualTo(UPDATED_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardConfig.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardConfig.getResizable()).isEqualTo(UPDATED_RESIZABLE);
        assertThat(testDashboardConfig.getSwap()).isEqualTo(UPDATED_SWAP);
        assertThat(testDashboardConfig.getPushItems()).isEqualTo(UPDATED_PUSH_ITEMS);
        assertThat(testDashboardConfig.getDisablePushOnDrag()).isEqualTo(UPDATED_DISABLE_PUSH_ON_DRAG);
        assertThat(testDashboardConfig.getDisablePushOnResize()).isEqualTo(UPDATED_DISABLE_PUSH_ON_RESIZE);
        assertThat(testDashboardConfig.getPushResizeItems()).isEqualTo(UPDATED_PUSH_RESIZE_ITEMS);
        assertThat(testDashboardConfig.getDisableWindowResize()).isEqualTo(UPDATED_DISABLE_WINDOW_RESIZE);
        assertThat(testDashboardConfig.getDisableWarnings()).isEqualTo(UPDATED_DISABLE_WARNINGS);
        assertThat(testDashboardConfig.getScrollToNewItems()).isEqualTo(UPDATED_SCROLL_TO_NEW_ITEMS);
    }

    @Test
    void patchNonExistingDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dashboardConfigDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDashboardConfig() throws Exception {
        int databaseSizeBeforeUpdate = dashboardConfigRepository.findAll().size();
        dashboardConfig.setId(UUID.randomUUID().toString());

        // Create the DashboardConfig
        DashboardConfigDTO dashboardConfigDTO = dashboardConfigMapper.toDto(dashboardConfig);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardConfigMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardConfigDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardConfig in the database
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDashboardConfig() throws Exception {
        // Initialize the database
        dashboardConfig.setId(UUID.randomUUID().toString());
        dashboardConfigRepository.save(dashboardConfig);

        int databaseSizeBeforeDelete = dashboardConfigRepository.findAll().size();

        // Delete the dashboardConfig
        restDashboardConfigMockMvc
            .perform(delete(ENTITY_API_URL_ID, dashboardConfig.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DashboardConfig> dashboardConfigList = dashboardConfigRepository.findAll();
        assertThat(dashboardConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
