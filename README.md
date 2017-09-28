This is a Domain Driven Design model for the domain of Tournaments.
We have considered players as a sub-domain.
The other domains are Matches(where all information related to the tournament, format.etc will be kept)
and Auction(Everything related to auctions like bidding a player ,etc will be kept)

#Domain : Players
Player is an aggregate for the players sub-domain.
A player is registered.
Information about player can be seen.

Constraints:
1. A player can be part of only 1 tournament at any given time.




Write Side: Players
Views:
1. Player_View(all information related to player)