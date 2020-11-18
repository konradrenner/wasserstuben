/*
 * Copyright (C) 2020 Konrad Renner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
export interface RealEstate{
    id: RealEstateId;
    owner: Owner[];
    fittings: CounterFitting[];
}

export interface RealEstateList{
    realestates: RealEstate[];
    totalNumber: number;
}

export interface RealEstateId{
    cadastralTownshipNumber: number;
    estateId: string;
    depositNumber: number;
    
}

export interface CounterFitting{
    id: string;
    description: string;
    manufacturer: string;
    installation: string;
    calibrations: string[];
}

export interface Owner{
    id: string;
    firstname: string;
    lastname: string;
}