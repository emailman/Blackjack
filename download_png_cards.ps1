$baseUrl = "https://raw.githubusercontent.com/hayeah/playing-cards-assets/master/png/"
$outputDir = "C:\Users\ericm\AndroidStudioProjects\Blackjack\composeApp\src\commonMain\composeResources\drawable"

$rankMap = @{
    "ace" = "ace"
    "2" = "2"
    "3" = "3"
    "4" = "4"
    "5" = "5"
    "6" = "6"
    "7" = "7"
    "8" = "8"
    "9" = "9"
    "10" = "10"
    "jack" = "jack"
    "queen" = "queen"
    "king" = "king"
}

$suits = @("clubs", "diamonds", "hearts", "spades")

# Remove old SVG files
Get-ChildItem $outputDir -Filter "card_*.svg" | Remove-Item -Force

foreach ($rank in $rankMap.Keys) {
    foreach ($suit in $suits) {
        $sourceFile = "${rank}_of_${suit}.png"
        $targetFile = "card_$($rankMap[$rank])_${suit}.png"
        $url = $baseUrl + $sourceFile
        $outputPath = Join-Path $outputDir $targetFile

        Write-Host "Downloading $sourceFile as $targetFile..."
        try {
            Invoke-WebRequest -Uri $url -OutFile $outputPath
        } catch {
            Write-Host "Failed to download $sourceFile : $_"
        }
    }
}

# Download card back
Write-Host "Downloading card back..."
Invoke-WebRequest -Uri ($baseUrl + "back.png") -OutFile (Join-Path $outputDir "card_back.png")

# Remove old card_back.svg
$oldBack = Join-Path $outputDir "card_back.svg"
if (Test-Path $oldBack) {
    Remove-Item $oldBack -Force
}

Write-Host "Done! Downloaded all cards."
Get-ChildItem $outputDir -Filter "card_*.png" | Select-Object -ExpandProperty Name | Sort-Object
