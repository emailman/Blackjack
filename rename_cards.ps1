Set-Location 'C:\Users\ericm\AndroidStudioProjects\Blackjack\composeApp\src\commonMain\composeResources\drawable'
Get-ChildItem -Filter '*.svg' | ForEach-Object {
    $newName = $_.Name.ToLower()
    $newName = 'card_' + $newName
    $newName = $newName -replace 'c\.svg', '_clubs.svg'
    $newName = $newName -replace 'd\.svg', '_diamonds.svg'
    $newName = $newName -replace 'h\.svg', '_hearts.svg'
    $newName = $newName -replace 's\.svg', '_spades.svg'
    if ($_.Name -ne $newName) {
        Rename-Item $_.FullName -NewName $newName
        Write-Host "Renamed $($_.Name) to $newName"
    }
}
Get-ChildItem -Filter 'card_*.svg' | Select-Object -ExpandProperty Name
