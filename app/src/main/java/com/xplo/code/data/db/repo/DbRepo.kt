package com.xplo.code.data.db.repo
import com.xplo.code.data.db.models.BeneficiaryEntity
import com.xplo.code.data.db.models.HouseholdItem
import com.xplo.code.data.db.offline.OptionItem
import com.xplo.code.data_module.core.Resource

/**
 * Copyright 2020 (C) xplo
 *
 * Created  : 6/2/20
 * Author   : xplo
 * Desc     :
 * Comment  :
 */

interface DbRepo {

    suspend fun insertFormPEntity(item: HouseholdItem, entity: BeneficiaryEntity): Resource<Unit>
    suspend fun getHousehold(id: String): Resource<HouseholdItem>
    suspend fun getHouseholds(): Resource<List<HouseholdItem>>
    suspend fun insertHousehold(item: HouseholdItem): Resource<Unit>
    suspend fun updateHousehold(item: HouseholdItem): Resource<Unit>
    suspend fun deleteHousehold(item: HouseholdItem): Resource<Unit>
    suspend fun getBeneficiary(id: String): Resource<BeneficiaryEntity>
    suspend fun getBeneficiaryItems(): Resource<List<BeneficiaryEntity>>
    suspend fun insertBeneficiary(item: BeneficiaryEntity): Resource<Unit>
    suspend fun updateBeneficiary(item: BeneficiaryEntity): Resource<Unit>
    suspend fun deleteBeneficiary(item: BeneficiaryEntity): Resource<Unit>
    suspend fun getOptionItems(
        column: String,
        argColName: String?,
        argColValue: String?
    ): Resource<List<OptionItem>>
    suspend fun getOptionItems2(
        columnCode: String,
        columnTitle: String,
        argColName: String?,
        argColValue: String?
    ): Resource<List<OptionItem>>

}