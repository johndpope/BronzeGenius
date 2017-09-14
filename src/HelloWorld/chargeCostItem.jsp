<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="aui-jsp-helpers" prefix="ajsp" %>
<%@ taglib prefix="a" uri="aui" %>
<%@ taglib prefix="hza" uri="horizonte-amazon-tags" %>
<%@ taglib prefix="oc" uri="operatorconsole" %>



<oc:masterPage
    pageTitleStringKey="OpConsole_webpage_Ocean_Booking_Menu_Title"
    menuBarType="regular"
    activeMenuItem="OpConsole_webpage_Ocean_Booking_Menu_Title"
    contentHeaderStringKey=""
    contentSubHeaderStringKey="">

    <link type="text/css" rel="stylesheet" href="/resources/css/main.css">

    <c:set var="inputItemGridNumber">4</c:set>
    <c:set var="FullGridUnit">12</c:set>
    <c:set var="inputItemInnerGridNumber">10</c:set>
    <c:set var="operationSuccessFeedbackContext"> <hza:string id="OpConsole_webpage_ChargeItem_Feedback_Success_Description" /> </c:set>
    <c:set var="operationFailureFeedbackContext"> <hza:string id="OpConsole_webpage_ChargeItem_Feedback_Failure_Description" /> </c:set>
    <c:set var="rootDropdownLabelContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Dropdown_Label" /> </c:set>
    <c:set var="chargeItemAddButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Button_Context" /> </c:set>
    <c:set var="detachButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Detach_Button_Context" /> </c:set>
    <c:set var="chargeItemSearchBoxPlaceHolderContext"> <hza:string id="OpConsole_webpage_ChargeItem_Search_Box_Input_Place_Holder_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingItemNameContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Item_Name_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingItemCategoryContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Item_Category_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingItemCostContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Item_Cost_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingQuantityContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Quantity_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingCurrencyContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Currency_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingAmountContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Amount_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingItemTimeContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Item_Time_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingDebitNoteContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Debit_Note_Context" /> </c:set>
    <c:set var="chargeItemTableColumnHeadingOperationContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Operation_Context" /> </c:set>
    <c:set var="costItemAddButtonContext"> <hza:string id="OpConsole_webpage_CostItem_Add_Button_Context" /> </c:set>
    <c:set var="costItemSearchBoxPlaceHolderContext"> <hza:string id="OpConsole_webpage_CostItem_Search_Box_Input_Place_Holder_Context" /> </c:set>
    <c:set var="costItemTableColumnHeadingItemTimeContext"> <hza:string id="OpConsole_webpage_CostItem_List_Table_Column_Heading_Cost_Item_Time_Context" /> </c:set>
    <c:set var="operationViewEditContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Operation_View_Edit_Context" /> </c:set>
    <c:set var="operationDeleteContext"> <hza:string id="OpConsole_webpage_ChargeItem_List_Table_Column_Heading_Operation_Delete_Context" /> </c:set>
    <c:set var="queryExceptionHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_Query_Exception_Heading_Context" /> </c:set>
    <c:set var="resultEmptyHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_Query_Result_Empty_Heading_Context" /> </c:set>
    <c:set var="queryExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_Query_Exception_Context" /> </c:set>
    <c:set var="resultEmptyContext"> <hza:string id="OpConsole_webpage_ChargeItem_Query_Result_Empty_Context" /> </c:set>
    <c:set var="addDialogHeaderContextContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_Header_Context" /> </c:set>
    <c:set var="addChargeItemDialogHeaderContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Header_Context" /> </c:set>
    <c:set var="addCostItemDialogHeaderContext"> <hza:string id="OpConsole_webpage_CostItem_Add_Dialog_Header_Context" /> </c:set>
    <c:set var="dialogButtonOKContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Dialog_Button_OK_Context" /> </c:set>
    <c:set var="dialogButtonCancelContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Dialog_Button_Cancel_Context" /> </c:set>
    <c:set var="addDialogItemNameExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_Item_Name_Exception_Context" /> </c:set>
    <c:set var="addDialogCurrencyExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_Currency_Exception_Context" /> </c:set>
    <c:set var="addDialogCostExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_Cost_Exception_Context" /> </c:set>
    <c:set var="addDialogQuantityExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_Quantity_Exception_Context" /> </c:set>
    <c:set var="addDialogAPCostExceptionContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Add_Dialog_AP_Cost_Exception_Context" /> </c:set>
    <c:set var="itemNameDropdownPromptContext"> <hza:string id="OpConsole_webpage_ChargeItem_Item_Name_Dropdown_Prompt_Context" /> </c:set>
    <c:set var="currencyDropdownPromptContext"> <hza:string id="OpConsole_webpage_ChargeItem_Currency_Dropdown_Prompt_Context" /> </c:set>
    <c:set var="addDialogItemNameTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Item_Name_Context" /> </c:set>
    <c:set var="addDialogFeeCategoryTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Fee_Category_Context" /> </c:set>
    <c:set var="addDialogCurrencyTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Currency_Context" /> </c:set>
    <c:set var="addDialogCostTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Cost_Context" /> </c:set>
    <c:set var="addDialogQuantityTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Quantity_Context" /> </c:set>
    <c:set var="addDialogAmountTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Amount_Context" /> </c:set>
    <c:set var="addDialogItemTimeTitleContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Title_Item_Time_Context" /> </c:set>
    <c:set var="addDialogRemarkPlaceHolderContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Remark_Place_Holder_Context" /> </c:set>
    <c:set var="viewEditDialogHeaderContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_View_Edit_Dialog_Header_Context" /> </c:set>
    <c:set var="chargeItemViewEditDialogHeaderContext"> <hza:string id="OpConsole_webpage_ChargeItem_View_Edit_Dialog_Header_Context" /> </c:set>
    <c:set var="costItemViewEditDialogHeaderContext"> <hza:string id="OpConsole_webpage_CostItem_View_Edit_Dialog_Header_Context" /> </c:set>
    <c:set var="chargeItemUneditableException_1_Context"> <hza:string id="OpConsole_webpage_ChargeItem_Uneditable_Exception_1_Context" /> </c:set>
    <c:set var="chargeItemUneditableException_2_Context"> <hza:string id="OpConsole_webpage_ChargeItem_Uneditable_Exception_2_Context" /> </c:set>
    <c:set var="chargeItemUneditableAlertHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_Uneditable_Alert_Heading_Context" /> </c:set>
    <c:set var="chargeItemDeleteDialogHeaderContext"> <hza:string id="OpConsole_webpage_ChargeItem_Delete_Dialog_Header_Context" /> </c:set>
    <c:set var="chargeItemUndeletableException_1_Context"> <hza:string id="OpConsole_webpage_ChargeItem_Undeletable_Exception_1_Context" /> </c:set>
    <c:set var="chargeItemUndeletableException_2_Context"> <hza:string id="OpConsole_webpage_ChargeItem_Undeletable_Exception_2_Context" /> </c:set>
    <c:set var="chargeItemUndeletableAlertHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_Undeletable_Alert_Heading_Context" /> </c:set>
    <c:set var="deleteDialogHeaderContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Delete_Dialog_Header_Context" /> </c:set>
    <c:set var="costItemDeleteDialogHeaderContext"> <hza:string id="OpConsole_webpage_CostItem_Delete_Dialog_Header_Context" /> </c:set>
    <c:set var="deleteDialogAlertHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Delete_Dialog_Alert_Heading_Context" /> </c:set>
    <c:set var="deleteDialogAlertContentContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Delete_Dialog_Alert_Content_Context" /> </c:set>
    <c:set var="detachDialogHeaderContextContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Detach_Dialog_Header_Context" /> </c:set>
    <c:set var="detachDialogAlertHeadingContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Detach_Dialog_Alert_Heading_Context" /> </c:set>
    <c:set var="detachDialogAlertContentContext"> <hza:string id="OpConsole_webpage_ChargeItem_CostItem_Detach_Dialog_Alert_Content_Context" /> </c:set>
    <c:set var="addDialogTriggerButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Add_Dialog_Trigger_Button_Context" /> </c:set>
    <c:set var="detachDialogTriggerButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Detach_Dialog_Trigger_Button_Context" /> </c:set>
    <c:set var="deleteDialogTriggerButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Delete_Dialog_Trigger_Button_Context" /> </c:set>
    <c:set var="undeletableDialogTriggerButtonContext"> <hza:string id="OpConsole_webpage_ChargeItem_Undeletable_Dialog_Trigger_Button_Context" /> </c:set>

    <ajsp:map var="i18nData"
              name="i18nData"
              feedbackExceptionDescription="${operationFailureFeedbackContext}"
              feedbackSuccessDescription="${operationSuccessFeedbackContext}"/>
    <a:pageState modelName="i18nData" stateData="${i18nData}" />
    <a:pageState modelName="bookingPaymentDetail" stateData="${bookingPaymentDetail}" />
    <a:pageState modelName="currencyCodeList" stateArrayData="${currencyCodeList}" />
    <a:pageState modelName="chargeCostItemConfigurationList" stateArrayData="${chargeCostItemConfigurationList}" />

    <oc:shipmentPageHead bookingId="${bookingId}" activePage="CostItems"/>

    <a:section>
        <header style="display: flex; padding-top: 20px; padding-bottom: 40px">
            <div style="flex: 0 0 auto; line-height: 29px; padding-right: 12px">
                <a:text style="vertical-align: middle">${rootDropdownLabelContext}</a:text>
            </div>
            <div style="width: 143.83px;">
                <a:dropdown nativeId="chargeCostItemFamilySelector" id="chargeCostItemFamilySelector" gridUnits="${FullGridUnit}">
                </a:dropdown>
            </div>
        </header>
    </a:section>

    <a:section id="ar-charge-item-container" cssClass="invisible">
        <header style="display: flex; padding-bottom: 20px;">
            <div style="flex: 0 0 auto; padding-right: 20px;">
                <a:declarativeAction actionName="addChargeItem">
                    <a:button buttonType="${'primary'}" id="addChargeItemButton">
                        ${chargeItemAddButtonContext}
                    </a:button>
                </a:declarativeAction>
            </div>
            <div style="flex: 1 0 auto">
                <c:if test="${bookingPaymentDetail.detached == false}">
                    <a:declarativeAction actionName="detachRateCard">
                        <a:button buttonType="${'primary'}" id="detachRateCardButton">
                            ${detachButtonContext}
                        </a:button>
                    </a:declarativeAction>
                </c:if>
            </div>
            <div style="flex: 0 0 auto">
                <a:textInput textInputType="${'search'}" placeholder="${chargeItemSearchBoxPlaceHolderContext}" name="search-ar-charge-item-input"
                cssClass="search-text-input"/>
            </div>
        </header>

        <a:table style="margin-top: 5px; padding-top: 40px;" tableType="${'bordered'}" id='ar-charge-item-table'>
            <thead>
            <a:tableRow>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemNameContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemCategoryContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemCostContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingQuantityContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingCurrencyContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingAmountContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemTimeContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingDebitNoteContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingOperationContext}
                </a:tableColumnHeading>
            </a:tableRow>
            <tbody id='ar-charge-item-table-body'>
                <a:tableRow cssClass="invisible">
                    <a:tableColumn valign="top" cssClass="dataTables_empty" colspan="10">${tableColumn_NoDataAvailable}</a:tableColumn>
                </a:tableRow>
                <c:forEach var="chargeItem" items="${bookingPaymentDetail.chargeItemList}">
                    <a:tableRow>
                        <a:tableColumn>
                            <%--c:forEach var="chargeCostItemConfiguration" items="${chargeCostItemConfigurationList}">
                                <c:if test="${chargeItem.chargeName == chargeCostItemConfiguration.code && bookingPaymentDetail.businessType == chargeCostItemConfiguration.businessType}">
                                    <c:set var="chargeItemName"><hza:string id="chargeCostItemConfiguration.lmsStringId"/></c:set>
                                    <c:set var="chargeItemNameTranslated" value="${fn:trim(chargeItemName)}" />
                                    <c:choose>
                                        <c:when test="${empty chargeItemNameTranslated}">
                                            empty
                                        </c:when>
                                        <c:otherwise>
                                            not empty
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach--%>
                            ${chargeItem.chargeName}
                        </a:tableColumn>
                        <a:tableColumn>${chargeItem.chargeCategory}</a:tableColumn>
                        <a:tableColumn>
                            ${chargeItem.unitPrice}
                        </a:tableColumn>
                        <a:tableColumn>
                            <fmt:parseNumber var="quantity" integerOnly="true" type="number" value="${chargeItem.num}"/>
                            <c:out value="${quantity}"/>
                        </a:tableColumn>
                        <a:tableColumn>${chargeItem.chargeCurrencyCode}</a:tableColumn>
                        <a:tableColumn>${chargeItem.amount}</a:tableColumn>
                        <a:tableColumn><oc:dateFormat timeStamp="${chargeItem.chargeDate}" showTime="true"/></a:tableColumn>
                        <a:tableColumn>${chargeItem.debitNoteId}</a:tableColumn>
                        <a:tableColumn>
                            <a:declarativeAction actionName="viewEditItem">
                                <a data-itemId="${chargeItem.chargeId}">${operationViewEditContext}</a>
                            </a:declarativeAction>
                            &nbsp;&nbsp;&nbsp;
                            <a:declarativeAction actionName="deleteItem">
                                <a data-itemId="${chargeItem.chargeId}">${operationDeleteContext}</a>
                            </a:declarativeAction>
                        </a:tableColumn>
                    </a:tableRow>
                </c:forEach>
            </tbody>
        </a:table>
    </a:section>

    <a:section id="ap-cost-item-container" cssClass="invisible">
        <header style="display: flex; padding-bottom: 20px;">
            <div style="flex: 1 0 auto">
                <a:declarativeAction actionName="addCostItem">
                    <a:button buttonType="${'primary'}" id="addCostItemButton">
                        ${costItemAddButtonContext}
                    </a:button>
                </a:declarativeAction>
            </div>
            <div style="flex: none">
                <a:textInput textInputType="${'search'}" placeholder="${costItemSearchBoxPlaceHolderContext}" name="search-ap-cost-item-input"
                cssClass="search-text-input"/>
            </div>
        </header>
        <a:table tableType="${'bordered'}" id='ap-cost-item-table'>
            <thead>
            <a:tableRow>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemNameContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemCategoryContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingItemCostContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingQuantityContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingCurrencyContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingAmountContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${costItemTableColumnHeadingItemTimeContext}
                </a:tableColumnHeading>
                <a:tableColumnHeading>
                    ${chargeItemTableColumnHeadingOperationContext}
                </a:tableColumnHeading>
            </a:tableRow>
            <tbody id='ap-cost-item-table-body'>
                <a:tableRow cssClass="invisible">
                    <a:tableColumn valign="top" cssClass="dataTables_empty" colspan="10">${tableColumn_NoDataAvailable}</a:tableColumn>
                </a:tableRow>
                <c:forEach var="costItem" items="${bookingPaymentDetail.costItemList}">
                    <a:tableRow>
                        <a:tableColumn>
                            <%--c:forEach var="chargeCostItemConfiguration" items="${chargeCostItemConfigurationList}">
                                <c:if test="${costItem.chargeName == chargeCostItemConfiguration.code && bookingPaymentDetail.businessType == chargeCostItemConfiguration.businessType}">
                                    <c:set var="costItemName">
                                        <hza:string id="chargeCostItemConfiguration.lmsStringId"/>
                                    </c:set>
                                    <c:if test='${fn:trim(costItemName) == ""}'>
                                        empty
                                    </c:if>
                                    <c:if test='${fn:trim(costItemName) != ""}'>
                                        not empty:${chargeItemName}--
                                    </c:if>
                                </c:if>
                            </c:forEach--%>
                            ${costItem.chargeName}
                        </a:tableColumn>
                        <a:tableColumn>${costItem.chargeCategory}</a:tableColumn>
                        <a:tableColumn>${costItem.unitPrice}</a:tableColumn>
                        <a:tableColumn>
                            <fmt:parseNumber var="quantity" integerOnly="true" type="number" value="${costItem.num}" />
                             <c:out value="${quantity}" />
                        </a:tableColumn>
                        <a:tableColumn>${costItem.chargeCurrencyCode}</a:tableColumn>
                        <a:tableColumn>${costItem.amount}</a:tableColumn>
                        <a:tableColumn><oc:dateFormat timeStamp="${costItem.chargeDate}" showTime="true"/></a:tableColumn>
                        <a:tableColumn>
                            <a:declarativeAction actionName="viewEditItem">
                                <a data-itemId="${costItem.chargeId}">${operationViewEditContext}</a>
                            </a:declarativeAction>
                            &nbsp;&nbsp;&nbsp;
                            <a:declarativeAction actionName="deleteItem">
                                <a data-itemId="${costItem.chargeId}">${operationDeleteContext}</a>
                            </a:declarativeAction>
                        </a:tableColumn>
                    </a:tableRow>
                </c:forEach>
            </tbody>
        </a:table>
    </a:section>

    <a:section id="exception-result-container" cssClass="invisible">
        <a:alert alertType="${'error'}" heading="${queryExceptionHeadingContext}">
            ${queryExceptionContext}
        </a:alert>
    </a:section>

    <a:section id="empty-result-container" cssClass="invisible">
        <a:alert alertType="${'info'}" heading="${resultEmptyHeadingContext}">
            ${resultEmptyContext}
        </a:alert>
    </a:section>

    <c:set var="addDialogHeader">
        ${addDialogHeaderContext}
    </c:set>

    <c:set var="addChargeItemDialogHeader">
        ${addChargeItemDialogHeaderContext}
    </c:set>

    <c:set var="addCostItemDialogHeader">
        ${addCostItemDialogHeaderContext}
    </c:set>

    <c:set var="addDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
        <a:declarativeAction actionName="addDialogSave">
            <a:button buttonType="${'primary'}" id="addDialogSaveButton">
                ${dialogButtonOKContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="addDialogAlert">
        <a:alert alertType="${'warning'}" heading="WARINING" id="addDialogAlert" cssClass="invisible">
            <ul id="addDialogAlertContent">
                <li id="itemCodeAlertContent" class="invisible">${addDialogItemNameExceptionContext}</li>
                <li id="currencyAlertContent" class="invisible">${addDialogCurrencyExceptionContext}</li>
                <li id="arCostAlertContent" class="invisible">${addDialogCostExceptionContext}</li>
                <li id="quantityAlertContent" class="invisible">${addDialogQuantityExceptionContext}</li>
            </ul>
        </a:alert>
    </c:set>
    <c:set var="addDialogSubContent">
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogItemNameTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:dropdown nativeId="itemCodeSelector" id="itemCodeSelector" gridUnits="${FullGridUnit}" optionPrompt="${itemNameDropdownPromptContext}">
                    <c:forEach var="chargeCostItemConfiguration" items="${chargeCostItemConfigurationList}">
                        <a:dropdownOption value="${chargeCostItemConfiguration.code}">${chargeCostItemConfiguration.defaultName}</a:dropdownOption>
                    </c:forEach>
                </a:dropdown>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogFeeCategoryTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="feeCategory" />
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogCurrencyTitleContext}" gridUnits="${inputItemGridNumber}" isLast="true">
                <a:dropdown nativeId="currencySelector" id="currencySelector" gridUnits="${FullGridUnit}" optionPrompt="${currencyDropdownPromptContext}">
                    <c:forEach var="currencyCode" items="${currencyCodeList}">
                        <a:dropdownOption value="${currencyCode}">${currencyCode}</a:dropdownOption>
                    </c:forEach>
                </a:dropdown>
            </oc:labeledControl>
        </a:gridRow>
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogCostTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:declarativeAction actionName="updateARAmount">
                    <a:textInput gridUnits="${FullGridUnit}" id="arCost" />
                </a:declarativeAction>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogQuantityTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:declarativeAction actionName="updateARAmount">
                    <a:textInput gridUnits="${FullGridUnit}" id="quantity" />
                </a:declarativeAction>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogAmountTitleContext}" gridUnits="${inputItemGridNumber}" isLast="true">
                <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="arAmount" />
            </oc:labeledControl>
        </a:gridRow>
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogItemTimeTitleContext}" gridUnits="${inputItemGridNumber}">
                 <a:gridColumn id="itemTimeCalendarContainer" gridUnits="${FullGridUnit}">
                    < a:calendar id="itemTimeCalendar" inputId="itemTimeCalendarInput" cssClass="calendarWide"/>
                </a:gridColumn>
            </oc:labeledControl>
        </a:gridRow>

        <a:gridRow spacingTop="large">
            <a:textArea placeholder="${addDialogRemarkPlaceHolderContext}"  id="remark" rows="4" />
        </a:gridRow>
    </c:set>
    <c:set var="addDialogContent">
        ${addDialogAlert}
        ${addDialogSubContent}
    </c:set>
    <ajsp:map var="addDialog"
              name="addDialog"
              header="${addDialogHeader}"
              chargeItemHeader="${addChargeItemDialogHeader}"
              costItemHeader="${addCostItemDialogHeader}"
              footer="${addDialogFooter}"
              inlineContent="${addDialogContent}"/>

    <c:set var="viewEditChargeItemDialogHeader">
        ${chargeItemViewEditDialogHeaderContext}
    </c:set>


    <c:set var="viewEditCostItemDialogHeader">
        ${costItemViewEditDialogHeaderContext}
    </c:set>

    <c:set var="viewEditDialogHeader">
        ${viewEditDialogHeaderContext}
    </c:set>

    <c:set var="viewEditDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
        <a:declarativeAction actionName="viewEditDialogSave">
            <a:button buttonType="${'primary'}" id="viewEditDialogSaveButton">
                ${dialogButtonOKContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="uneditableDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
        <a:declarativeAction actionName="uneditableDialogSave">
            <a:button buttonType="${'primary'}" id="uneditableDialogSaveButton">
                ${dialogButtonOKContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="uneditableDialogAlert">
        <a:alert alertType="${'error'}" heading="${chargeItemUneditableAlertHeadingContext}">
           <ul>
               <li>${chargeItemUneditableException_1_Context}</li>
               <li>${chargeItemUneditableException_2_Context}</li>
           </ul>
       </a:alert>
    </c:set>
    <c:set var="uneditableDialogSubContent">
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogItemNameTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:dropdown status="disabled" nativeId="itemCodeSelector" id="itemCodeSelector" gridUnits="${FullGridUnit}" optionPrompt="${itemNameDropdownPromptContext}">
                    <c:forEach var="chargeCostItemConfiguration" items="${chargeCostItemConfigurationList}">
                        <a:dropdownOption value="${chargeCostItemConfiguration.code}">${chargeCostItemConfiguration.defaultName}</a:dropdownOption>
                    </c:forEach>
                </a:dropdown>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogFeeCategoryTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="feeCategory" />
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogCurrencyTitleContext}" gridUnits="${inputItemGridNumber}" isLast="true">
                <a:dropdown status="disabled" nativeId="currencySelector" id="currencySelector" gridUnits="${FullGridUnit}" optionPrompt="${currencyDropdownPromptContext}">
                    <c:forEach var="currencyCode" items="${currencyCodeList}">
                        <a:dropdownOption value="${currencyCode}">${currencyCode}</a:dropdownOption>
                    </c:forEach>
                </a:dropdown>
            </oc:labeledControl>
        </a:gridRow>
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogCostTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:declarativeAction actionName="updateARAmount">
                    <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="arCost" />
                </a:declarativeAction>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogQuantityTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:declarativeAction actionName="updateARAmount">
                    <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="quantity" />
                </a:declarativeAction>
            </oc:labeledControl>
            <oc:labeledControl title="${addDialogAmountTitleContext}" gridUnits="${inputItemGridNumber}" isLast="true">
                <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="arAmount" />
            </oc:labeledControl>
        </a:gridRow>
        <a:gridRow spacingTop="large">
            <oc:labeledControl title="${addDialogItemTimeTitleContext}" gridUnits="${inputItemGridNumber}">
                <a:textInput status="disabled" gridUnits="${FullGridUnit}" id="itemTime" />
                <a:hiddenInput status="disabled" gridUnits="${FullGridUnit}" id="itemTimeValue" />
            </oc:labeledControl>
        </a:gridRow>

        <a:gridRow spacingTop="large">
            <a:textArea placeholder="${addDialogRemarkPlaceHolderContext}"  id="remark" rows="4" />
        </a:gridRow>
    </c:set>
    <c:set var="uneditableDialogContent">
        ${uneditableDialogAlert}
        ${uneditableDialogSubContent}
    </c:set>
    <ajsp:map var="viewEditDialog"
              name="viewEditDialog"
              header="${viewEditDialogHeader}"
              costItemHeader="${viewEditCostItemDialogHeader}"
              chargeItemHeader="${viewEditChargeItemDialogHeader}"
              footer="${viewEditDialogFooter}"
              inlineContent="${addDialogContent}"
              uneditableHeader="${viewEditChargeItemDialogHeader}"
              uneditableFooter="${uneditableDialogFooter}"
              uneditableInlineContent="${uneditableDialogContent}"/>


    <!-- The definition of not deletable dialog -->
    <c:set var="undeletableDialogHeader">
        ${chargeItemDeleteDialogHeaderContext}
    </c:set>

    <c:set var="undeletableDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="undeletableDialogContent">
       <a:alert alertType="${'error'}" heading="${chargeItemUndeletableAlertHeadingContext}">
           <ul>
               <li>${chargeItemUndeletableException_1_Context}</li>
               <li>${chargeItemUndeletableException_2_Context}</li>
           </ul>
       </a:alert>
    </c:set>

    <ajsp:map var="undeletableDialog"
              name="undeletableDialog"
              header="${undeletableDialogHeader}"
              footer="${undeletableDialogFooter}"
              inlineContent="${undeletableDialogContent}"/>



    <!-- The definition of delete dialog -->
    <c:set var="deleteDialogHeader">
        ${deleteDialogHeaderContext}
    </c:set>

    <c:set var="deleteChargeItemDialogHeader">
        ${chargeItemDeleteDialogHeaderContext}
    </c:set>

    <c:set var="deleteCostItemDialogHeader">
        ${costItemDeleteDialogHeaderContext}
    </c:set>

    <c:set var="deleteDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
        <a:declarativeAction actionName="deleteDialogSave">
            <a:button buttonType="${'primary'}" id="deleteDialogSaveButton">
                ${dialogButtonOKContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="deleteDialogContent">
            <a:alert alertType="${'warning'}" heading="${deleteDialogAlertHeadingContext}">
                ${deleteDialogAlertContentContext}
            </a:alert>
            <a:hiddenInput id="itemIdToBeDeleted" />
    </c:set>

    <ajsp:map var="deleteDialog"
              name="deleteDialog"
              header="${deleteDialogHeader}"
              chargeItemHeader="${deleteChargeItemDialogHeader}"
              costItemHeader="${deleteCostItemDialogHeader}"
              footer="${deleteDialogFooter}"
              inlineContent="${deleteDialogContent}"/>

    <!-- The definition of detach dialog -->
    <c:set var="detachDialogHeader">
        ${detachDialogHeaderContextContext}
    </c:set>

    <c:set var="detachDialogFooter">
        <a:declarativeAction actionName="a-popover-close">
            <a:button>
                ${dialogButtonCancelContext}
            </a:button>
        </a:declarativeAction>
        <a:declarativeAction actionName="detachDialogSave">
            <a:button buttonType="${'primary'}" id="detachDialogButton">
                ${dialogButtonOKContext}
            </a:button>
        </a:declarativeAction>
    </c:set>

    <c:set var="detachDialogContent">
            <a:alert alertType="${'warning'}" heading="${detachDialogAlertHeadingContext}">
                ${detachDialogAlertContentContext}
            </a:alert>
    </c:set>
    <ajsp:map var="detachDialog"
              name="detachDialog"
              header="${detachDialogHeader}"
              footer="${detachDialogFooter}"
              inlineContent="${detachDialogContent}"/>

    <a:pageState modelName="addDialog" stateData="${addDialog}" />
    <a:pageState modelName="detachDialog" stateData="${detachDialog}" />
    <a:pageState modelName="viewEditDialog" stateData="${viewEditDialog}" />
    <a:pageState modelName="deleteDialog" stateData="${deleteDialog}" />
    <a:pageState modelName="undeletableDialog" stateData="${undeletableDialog}" />

    <div class="invisible">
        <a:button id="add-modal-trigger" buttonType="${'primary'}">
            ${addDialogTriggerButtonContext}
        </a:button>

        <a:button id="detach-modal-trigger" buttonType="${'primary'}">
            ${detachDialogTriggerButtonContext}
        </a:button>

        <a:button id="delete-modal-trigger" buttonType="${'primary'}">
            ${deleteDialogTriggerButtonContext}
        </a:button>

        <a:button id="undeletable-modal-trigger" buttonType="${'primary'}">
            ${undeletableDialogTriggerButtonContext}
        </a:button>
    </div>

    <a:section cssClass="invisible">
        <a:form action="/chargeItem/home">
            <oc:labeledControl title="Create Rate Card" gridUnits="4">
                <a:textArea name="data" gridUnits="10" rows="1" />
                <a:button id="queryButton" buttonType="${'primary'}" gridUnits="${inputItemInnerGridNumber}">
                    GO
                </a:button>
            </oc:labeledControl>

            <%-- a:hiddenInput id="actorCategory" name="actorCategory" value="CS" / --%>
            <a:hiddenInput id="targetId" name="targetId" value="RC999999" />
            <a:hiddenInput id="targetCategory" name="targetCategory" value="RateCard" />
            <a:hiddenInput id="behaviorCategory" name="behaviorCategory" value="Create"/>
            <%--a:hiddenInput id="data" name="data" value="test:data" /--%>
        </a:form>
    </a:section>

    <%--script type="text/javascript" charset="utf8" src="/resources/js/charge-cost-item.js"></script--%>
    <script>
        P.when("A", "a-calendar", "charge-cost-item-initializer", "ready").execute(function(A, calendar, initializer) {
            initializer.initialize();
        });
    </script>
</oc:masterPage>