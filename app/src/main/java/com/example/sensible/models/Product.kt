package com.example.sensible.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @SerializedName("added_countries_tags")
    var addedCountriesTags: String?,
    @SerializedName("allergens")
    var allergens: String?,
    @SerializedName("allergens_from_ingredients")
    var allergensFromIngredients: String?,
    @SerializedName("allergens_from_user")
    var allergensFromUser: String?,
    @SerializedName("allergens_hierarchy")
    var allergensHierarchy: String?,
    @SerializedName("allergens_tags")
    var allergensTags: String?,
    @SerializedName("brands")
    var brands: String?,
    @SerializedName("brands_tags")
    var brandsTags: String?,
    @SerializedName("categories_properties")
    var categoriesProperties: String?,
    @SerializedName("categories_properties_tags")
    var categoriesPropertiesTags: String?,
    @SerializedName("checkers_tags")
    var checkersTags: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("codes_tags")
    var codesTags: String?,
    @SerializedName("complete")
    var complete: String?,
    @SerializedName("completeness")
    var completeness: String?,
    @SerializedName("correctors_tags")
    var correctorsTags: String?,
    @SerializedName("countries")
    var countries: String?,
    @SerializedName("countries_hierarchy")
    var countriesHierarchy: String?,
    @SerializedName("countries_tags")
    var countriesTags: String?,
    @SerializedName("created_t")
    var createdT: String?,
    @SerializedName("creator")
    var creator: String?,
    @SerializedName("data_quality_bugs_tags")
    var dataQualityBugsTags: String?,
    @SerializedName("data_quality_errors_tags")
    var dataQualityErrorsTags: String?,
    @SerializedName("data_quality_info_tags")
    var dataQualityInfoTags: String?,
    @SerializedName("data_quality_tags")
    var dataQualityTags: String?,
    @SerializedName("data_quality_warnings_tags")
    var dataQualityWarningsTags: String?,
    @SerializedName("data_sources")
    var dataSources: String?,
    @SerializedName("data_sources_tags")
    var dataSourcesTags: String?,
    @SerializedName("ecoscore_data")
    var ecoscoreData: String?,
    @SerializedName("ecoscore_grade")
    var ecoscoreGrade: String?,
    @SerializedName("ecoscore_tags")
    var ecoscoreTags: String?,
    @SerializedName("editors_tags")
    var editorsTags: String?,
    @SerializedName("entry_dates_tags")
    var entryDatesTags: String?,
    @SerializedName("food_groups_tags")
    var foodGroupsTags: String?,
    @SerializedName("_id")
    var other_id: Long,
    @Expose
    @PrimaryKey
    @SerializedName("id")
    var id: Long,
    @SerializedName("image_front_small_url")
    var imageFrontSmallUrl: String?,
    @SerializedName("image_front_thumb_url")
    var imageFrontThumbUrl: String?,
    @SerializedName("image_front_url")
    var imageFrontUrl: String?,
    @SerializedName("image_nutrition_small_url")
    var imageNutritionSmallUrl: String?,
    @SerializedName("image_nutrition_thumb_url")
    var imageNutritionThumbUrl: String?,
    @SerializedName("image_nutrition_url")
    var imageNutritionUrl: String?,
    @SerializedName("image_small_url")
    var imageSmallUrl: String?,
    @SerializedName("image_thumb_url")
    var imageThumbUrl: String?,
    @Expose
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("images")
    var images: String?,
    @SerializedName("informers_tags")
    var informersTags: String?,
    @SerializedName("interface_version_created")
    var interfaceVersionCreated: String?,
    @SerializedName("interface_version_modified")
    var interfaceVersionModified: String?,
    @SerializedName("_keywords")
    var keywords: String?,
    @SerializedName("labels")
    var labels: String?,
    @SerializedName("labels_hierarchy")
    var labelsHierarchy: String?,
    @SerializedName("labels_lc")
    var labelsLc: String?,
    @SerializedName("labels_tags")
    var labelsTags: String?,
    @SerializedName("lang")
    var lang: String?,
    @SerializedName("languages")
    var languages: String?,
    @SerializedName("languages_codes")
    var languagesCodes: String?,
    @SerializedName("languages_hierarchy")
    var languagesHierarchy: String?,
    @SerializedName("languages_tags")
    var languagesTags: String?,
    @SerializedName("last_edit_dates_tags")
    var lastEditDatesTags: String?,
    @SerializedName("last_editor")
    var lastEditor: String?,
    @SerializedName("last_image_dates_tags")
    var lastImageDatesTags: String?,
    @SerializedName("last_image_t")
    var lastImageT: String?,
    @SerializedName("last_modified_by")
    var lastModifiedBy: String?,
    @SerializedName("last_modified_t")
    var lastModifiedT: String?,
    @SerializedName("lc")
    var lc: String?,
    @SerializedName("main_countries_tags")
    var mainCountriesTags: String?,
    @SerializedName("max_imgid")
    var maxImgid: String?,
    @SerializedName("misc_tags")
    var miscTags: String?,
    @SerializedName("nova_group_debug")
    var novaGroupDebug: String?,
    @SerializedName("nova_group_tags")
    var novaGroupTags: String?,
    @SerializedName("nutrient_levels")
    var nutrientLevels: String?,
    @SerializedName("nutrient_levels_tags")
    var nutrientLevelsTags: String?,
    @Expose
    @SerializedName("nutriments")
    var nutriments: Nutriments,
    @SerializedName("nutrition_data_per")
    var nutritionDataPer: String?,
    @SerializedName("nutrition_data_prepared_per")
    var nutritionDataPreparedPer: String?,
    @SerializedName("nutrition_grades_tags")
    var nutritionGradesTags: String?,
    @SerializedName("nutrition_score_beverage")
    var nutritionScoreBeverage: String?,
    @SerializedName("nutrition_score_debug")
    var nutritionScoreDebug: String?,
    @SerializedName("packagings")
    var packagings: String?,
    @SerializedName("photographers_tags")
    var photographersTags: String?,
    @SerializedName("pnns_groups_1")
    var pnnsGroups1: String?,
    @SerializedName("pnns_groups_1_tags")
    var pnnsGroups1Tags: String?,
    @SerializedName("pnns_groups_2")
    var pnnsGroups2: String?,
    @SerializedName("pnns_groups_2_tags")
    var pnnsGroups2Tags: String?,
    @SerializedName("popularity_key")
    var popularityKey: String?,
    @SerializedName("popularity_tags")
    var popularityTags: String?,
    @Expose
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("product_name_de")
    var productNameDe: String?,
    @SerializedName("removed_countries_tags")
    var removedCountriesTags: String?,
    @SerializedName("rev")
    var rev: String?,
    @SerializedName("scans_n")
    var scansN: String?,
    @SerializedName("selected_images")
    var selectedImages: String?,
    @SerializedName("states")
    var states: String?,
    @SerializedName("states_hierarchy")
    var statesHierarchy: String?,
    @SerializedName("states_tags")
    var statesTags: String?,
    @SerializedName("stores")
    var stores: String?,
    @SerializedName("stores_tags")
    var storesTags: String?,
    @SerializedName("teams")
    var teams: String?,
    @SerializedName("teams_tags")
    var teamsTags: String?,
    @SerializedName("traces")
    var traces: String?,
    @SerializedName("traces_from_ingredients")
    var tracesFromIngredients: String?,
    @SerializedName("traces_from_user")
    var tracesFromUser: String?,
    @SerializedName("traces_hierarchy")
    var tracesHierarchy: String?,
    @SerializedName("traces_tags")
    var tracesTags: String?,
    @SerializedName("unique_scans_n")
    var uniqueScansN: String?,
    @SerializedName("unknown_nutrients_tags")
    var unknownNutrientsTags: String?,
    @SerializedName("update_key")
    var updateKey: String?
){}