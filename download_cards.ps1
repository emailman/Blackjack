$baseUrl = "https://raw.githubusercontent.com/notpeter/Vector-Playing-Cards/master/cards-svg/"
$outputDir = "C:\Users\ericm\AndroidStudioProjects\Blackjack\composeApp\src\commonMain\composeResources\drawable"

$suitMap = @{
    "C" = "clubs"
    "D" = "diamonds"
    "H" = "hearts"
    "S" = "spades"
}

$rankMap = @{
    "A" = "ace"
    "2" = "2"
    "3" = "3"
    "4" = "4"
    "5" = "5"
    "6" = "6"
    "7" = "7"
    "8" = "8"
    "9" = "9"
    "10" = "10"
    "J" = "jack"
    "Q" = "queen"
    "K" = "king"
}

foreach ($rank in $rankMap.Keys) {
    foreach ($suit in $suitMap.Keys) {
        $sourceFile = "${rank}${suit}.svg"
        $targetFile = "card_$($rankMap[$rank])_$($suitMap[$suit]).svg"
        $url = $baseUrl + $sourceFile
        $outputPath = Join-Path $outputDir $targetFile

        Write-Host "Downloading $sourceFile as $targetFile..."
        Invoke-WebRequest -Uri $url -OutFile $outputPath
    }
}

Write-Host "Done! Downloaded all 52 cards."
Get-ChildItem $outputDir -Filter "card_*.svg" | Select-Object -ExpandProperty Name | Sort-Object
