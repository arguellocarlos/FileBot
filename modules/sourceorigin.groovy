{
    def sourceMap = [
        'AMZN'   : 'Amazon',
        'CR'     : 'Crunchyroll',
        'DSNP'   : 'Disney Plus',
        'HIDIVE' : 'HIDIVE',
        'NF'     : 'Netflix',
        'YT'     : 'YouTube'
    ]
    
    // Known release groups and their sources
    def releaseGroupMap = [
        'SubsPlease' : 'Crunchyroll',
        'Erai-raws'  : 'Other'  // Default folder for Erai-raws when no source tag found
    ]
    
    // First check for SubsPlease
    if (group_.matches(/(?i).*SubsPlease.*/) || fn.matches(/(?i).*SubsPlease.*/)) {
        return "Crunchyroll"
    }
    
    // Then check for Erai-raws with source tags
    if (group_.matches(/(?i).*Erai-raws.*/) || fn.matches(/(?i).*Erai-raws.*/)) {
        def source = null
        // Look for source tags in brackets
        def matcher = fn =~ /\[(.*?)\]/
        def allTags = []
        while (matcher.find()) {
            allTags << matcher.group(1)
        }
        
        // Check each tag for a source
        for (tag in allTags) {
            def srcTag = sourceMap.find { key, value -> 
                tag.matches(/(?i).*${key}.*/)
            }
            if (srcTag) {
                source = srcTag.value
                break
            }
        }
        return source ?: "Other"  // Return Other if no source tag found
    }
    
    // For other releases, check source tags anywhere
    def srcTag = sourceMap.find { tag, name ->
        group_.matches(/(?i).*${tag}.*/) || fn.matches(/(?i).*${tag}.*/)
    }
    
    return srcTag ? srcTag.value : "Unsorted"  // Always return a valid folder name
}