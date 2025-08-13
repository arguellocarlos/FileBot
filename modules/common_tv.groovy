{"Videos/"}
@./r18_checker.groovy
{
    // check Country of Origin
    def cjk_countries = ["CN", "JP", "KR", "KP", "TW", "HK"]
    def cjkani_tags = ["aeni", "donghua", "anime"]
    def is_anime = any {anime} || info.Keywords.findAll { it in cjkani_tags } || (cjk_countries.contains(country) && genres =~ /Animation/)
    // Categorized path
    is_anime ? "Anime/" : "TV Series/"
}
@./medianame.groovy
@./forceshowid_tv.groovy
@./season.groovy
@./filename_tv.groovy