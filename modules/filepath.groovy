{
    // detect if the system is windows
    def gp_ = { System.getProperty(it) }
    def is_windows = gp_("os.name").toLowerCase().contains("windows")
    // get current username
    def user_ = gp_("user.name")

    // Arch Linux mountpoint
    def mntp = "~/Videos/Anime/${user_}"

    //! OVERRIDE THIS PATH IF NEEDED, ELSE, BLANK IT ("")
    def override = is_windows ? "E:/" : "$mntp/Videos"

    def mounts = [
        [label: "Videos", winmnt: "E:/", linmnt: "$mntp/Videos"],
    ]

    def guess = (mounts.collect { it[is_windows ? "winmnt" : "linmnt"] as File }.sort { first, second -> first.exists() <=> second.exists() ?: first.diskSpace <=> second.diskSpace }).last()
    def final_ = override ?: guess
    "$final_/"
}
