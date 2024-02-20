package com.xplo.code.ui.dashboard.model

import com.xplo.code.core.TestConfig
import java.io.Serializable

/**
 * Copyright 2022 (C) xplo
 *
 * Created  : 22/06/28
 * Author   : xplo
 * Desc     :
 * Comment  :
 */

data class HouseholdForm(

    var id: Long = -1L,     // optional, use at some specific case
    var uuid: String? = null,

    var form1: HhForm1? = null,
    var form2: HhForm2? = null,
    var form3: HhForm3? = null,
    var form4: HhForm4? = null,
    var form5: HhForm5? = null,
    var form6: HhForm6? = null,
    var alternates: ArrayList<AlternateForm> = arrayListOf(),
    var consentStatus: ConsentStatus = ConsentStatus()
)

data class ConsentStatus(
    var isConsentGivenHhHome: Boolean = false,
    var isConsentGivenNominee: Boolean = false
)

data class AlternateForm(
    var form1: AlForm1? = null,
    var form2: AlForm2? = null,
    var form3: AlForm3? = null
) : Serializable

data class HhForm1(
    var state: Area? = Area(),
    var county: Area? = Area(),
    var payam: Area? = Area(),
    var boma: Area? = Area(),
    var lat: Double? = null,
    var lon: Double? = null
)

data class Area(
    var id: Int? = null,
    var name: String? = null
)

data class HhForm2(
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var spouseFirstName: String? = null,
    var spouseMiddleName: String? = null,
    var spouseLastName: String? = null,
    var age: Int? = null,
    var idNumber: String? = null,
    var idNumberType: String? = null,
    var phoneNumber: String? = null,
    var mainSourceOfIncome: String? = null,
    var currency: String? = null,
    var monthlyAverageIncome: Int? = null,
    var gender: String? = null,
    var respondentRlt: String? = null,
    var maritalStatus: String? = null,
    var legalStatus: String? = null,
    //var spouseName: String? = null,
    var selectionReason: String? = null,
    var idIsOrNot: String? = null,
    var selectionCriteria: String? = null,
    var itemsSupportType: List<CheckboxItem>? = null
)

fun HhForm2?.getOppositeGender(): String? {
//    if (TestConfig.isNavHackEnabled) {
//        return "Female" // test purpose
//    }
    if (this == null) return null
    if (this.gender.equals("Male", true)) return "Female"
    return "Male"
}

data class HhForm3(
    var householdSize: Int? = null,

    var male0_2: HhMember = HhMember(),
    var male3_5: HhMember = HhMember(),
    var male6_17: HhMember = HhMember(),
    var male18_35: HhMember = HhMember(),
    var male36_64: HhMember = HhMember(),
    var male65p: HhMember = HhMember(),

    var female0_2: HhMember = HhMember(),
    var female3_5: HhMember = HhMember(),
    var female6_17: HhMember = HhMember(),
    var female18_35: HhMember = HhMember(),
    var female36_64: HhMember = HhMember(),
    var female65p: HhMember = HhMember(),

    var readWriteNumber: Int? = null,
    var isReadWrite: String? = null

)

data class HhMember(
    var normal: Int = 0,
    var ill: Int = 0,
    var disable: Int = 0
)

data class HhForm4(
    var img: String? = null
)

fun HhForm4.isOk(): Boolean {
    if (!TestConfig.isValidationEnabled) return true
    if (this.img == null) return false
    if (this.img.isNullOrEmpty()) return false

    return true
}

data class HhForm6(

    var isNomineeAdd: String? = null,       // nominee add or not
    var noNomineeReason: String? = null,    // spinner
    var otherReason: String? = null,    // others box
    var nominees: ArrayList<Nominee> = arrayListOf(),

    // under list
    var xIsNomineeAdd: String? = null,
    var xNoNomineeReason: String? = null,
    var xOtherReason: String? = null,

    )

fun HhForm6?.getNomineeNumber(): Int {
    if (this == null) return 1
    return this.nominees.size.plus(1)
}

data class Nominee(
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var relation: String? = null,
    var age: Int? = null,
    var gender: String? = null,
    var occupation: String? = null,
    var isReadWrite: String? = null
)

fun Nominee?.getOppositeGender(): String? {
    if (this == null) return null
    if (this.gender.equals("Male", true)) return "Female"
    return "Male"
}

fun Nominee?.getNomineeHeader(number: Int): String {
    when (number) {
        1 -> return "First Nominee"
        2 -> return "Second Nominee"
        3 -> return "Third Nominee"
        4 -> return "Fourth Nominee"
        5 -> return "Fifth Nominee"
        else -> return "Nominee $number"
    }
}

fun Nominee?.toSummary(): String? {
    if (this == null) return null

    var sb = StringBuilder()
        .append("Name: " + this.getFullName())
        .append("\nRelation: " + this.relation)
//        .append("\nAge: " + this.age)
        .append("\nGender: " + this.gender)
//        .append("\nOccupation: " + this.occupation)
//        .append("\nCan read write: " + this.isReadWrite)


    var txt = sb.toString()
    return txt
}

fun Nominee?.toDetails(): String? {
    if (this == null) return null

    var sb = StringBuilder()
        .append("Name: " + this.getFullName())
        .append("\nRelation: " + this.relation)
        .append("\nAge: " + this.age)
        .append("\nGender: " + this.gender)
        .append("\nOccupation: " + this.occupation)
        .append("\nCan read write: " + this.isReadWrite)


    var txt = sb.toString()
    return txt
}


data class HhForm5(
    var finger: Finger? = null
)

data class AlForm1(
    var householdName: String? = null,

    var alternateFirstName: String? = null,
    var alternateMiddleName: String? = null,
    var alternateLastName: String? = null,

    var age: Int? = null,
    var idNumber: String? = null,
    var idNumberType: String? = null,
    var idIsOrNot: String? = null,
    var phoneNumber: String? = null,
    var selectAlternateRlt: String? = null,
    var gender: String? = null
) : Serializable

data class AlForm2(
    var img: String? = null
) : Serializable

fun AlForm2.isOk(): Boolean {
    if (!TestConfig.isValidationEnabled) return true
    if (this.img == null) return false
    if (this.img.isNullOrEmpty()) return false

    return true
}

data class AlForm3(
    var finger: Finger? = null
) : Serializable

data class Finger(
    var fingerRT: String? = null,
    var fingerRI: String? = null,
    var fingerRM: String? = null,
    var fingerRR: String? = null,
    var fingerRL: String? = null,
    var fingerLT: String? = null,
    var fingerLI: String? = null,
    var fingerLM: String? = null,
    var fingerLR: String? = null,
    var fingerLL: String? = null
) : Serializable

